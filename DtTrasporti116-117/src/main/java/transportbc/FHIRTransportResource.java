/*
 * Copyright (c) 2022. Giada Gibertoni
 */

package transportbc;

import sharedkernel.FHIRParser;
import transportbc.dtmodel.TransportDtModel;
import transportbc.dtmodel.TransportRoute;
import org.hl7.fhir.r4.model.*;
import org.jetbrains.annotations.NotNull;

import java.time.ZoneId;
import java.util.Date;
import java.util.List;

public class FHIRTransportResource {
    public static String createTransportAppointmentFHIRResource(@NotNull TransportDtModel dtTransport, @NotNull String ambulanceId, @NotNull String patientId) {
        DomainResource transport = new Appointment()
                .addIdentifier(new Identifier().setValue(dtTransport.getId() + TransportConstants.APPOINTMENT))
                .addServiceCategory(getServiceCategory())
                .addServiceType(getServiceType())
                .setStart(Date.from(dtTransport.getStartDateTime().atZone(ZoneId.systemDefault()).toInstant()))
                .setParticipant(List.of(
                        new Appointment.AppointmentParticipantComponent()
                                .setActor(getPatientRef(patientId))
                                .setStatus(Appointment.ParticipationStatus.ACCEPTED),
                        new Appointment.AppointmentParticipantComponent()
                                .setActor(getAmbulanceReference(ambulanceId))
                                .setStatus(Appointment.ParticipationStatus.ACCEPTED)))
                .setContained(List.of(getDepartureRoute(dtTransport.getRoute()), getDestinationRoute(dtTransport.getRoute())));

        return FHIRParser.getParser().encodeResourceToString(transport);
    }

    public static String createTransportEncounterFHIRResource(@NotNull TransportDtModel dtTransport, @NotNull String ambulanceId, @NotNull String patientId, @NotNull String operatorId) {
        Procedure transportProcedure = new Procedure()
                .addIdentifier(new Identifier().setValue(dtTransport.getId() + TransportConstants.PROCEDURE))

                .setCode(getProcedureCode())
                .setSubject(getPatientRef(patientId))
                .setPerformer(List.of(
                        new Procedure.ProcedurePerformerComponent().setActor(getOperatorReference(operatorId)),
                        new Procedure.ProcedurePerformerComponent().setActor(getAmbulanceReference(ambulanceId))))
                .setEncounter(new Reference().setReference(dtTransport.getId() + TransportConstants.ENCOUNTER));

        Encounter transportEncounter = new Encounter()
                .addIdentifier(new Identifier().setValue(dtTransport.getId() + TransportConstants.ENCOUNTER))
                .setClass_(new Coding().setCode(TransportConstants.ENCOUNTER_ACT_CODE).setSystem(TransportConstants.HL7_SYSTEMS_ACT_CODE))
                .setSubject(getPatientRef(patientId))
                .setServiceType(getServiceType())
                .setParticipant(List.of(
                        new Encounter.EncounterParticipantComponent()
                                .addType(getOperatorParticipationType())
                                .setIndividual(getOperatorReference(operatorId)),
                        new Encounter.EncounterParticipantComponent()
                                .addType(getAmbulanceParticipationType())
                                .setIndividual(getAmbulanceReference(ambulanceId))))
                .addAppointment(new Reference().setReference(dtTransport.getId() + TransportConstants.APPOINTMENT))
                .setPeriod(new Period().setStart(Date.from(dtTransport.getStartDateTime().atZone(ZoneId.systemDefault()).toInstant())))
                .setLocation(List.of(
                        new Encounter.EncounterLocationComponent().setLocationTarget(getDepartureRoute(dtTransport.getRoute())),
                        new Encounter.EncounterLocationComponent().setLocationTarget(getDestinationRoute(dtTransport.getRoute()))));

        switch (dtTransport.getPhase()){
            case IN_PROGRESS -> {
                transportProcedure.setStatus(Procedure.ProcedureStatus.INPROGRESS);
                transportEncounter.setStatus(Encounter.EncounterStatus.INPROGRESS);
            }
            case COMPLETED -> {
                transportProcedure.setStatus(Procedure.ProcedureStatus.COMPLETED);
                transportEncounter.setStatus(Encounter.EncounterStatus.FINISHED);
            }
            case SCHEDULED -> {
                return createTransportAppointmentFHIRResource(dtTransport, ambulanceId, patientId);
            }
        }

        transportEncounter.addContained(transportProcedure);
        return FHIRParser.getParser().encodeResourceToString(transportEncounter);
    }

    public static Appointment parseAppointmentFHIRResource(String jsonResource){
        return FHIRParser.getParser().parseResource(Appointment.class, jsonResource);
    }

    public static Encounter parseEncounterFHIRResource(String jsonResource){
        return FHIRParser.getParser().parseResource(Encounter.class, jsonResource);
    }

    private static Reference getPatientRef(@NotNull String patientId){
        return new Reference().setReference(TransportConstants.PATIENT_REF + patientId);
    }

    private static Reference getOperatorReference(@NotNull String operatorId){
        return new Reference().setReference(TransportConstants.PRACTITIONER_REF + operatorId);
    }

    private static Reference getAmbulanceReference(@NotNull String ambulanceId){
        return new Reference().setReference(TransportConstants.DEVICE_REF + ambulanceId);
    }

    private static CodeableConcept getProcedureCode(){
        return new CodeableConcept().addCoding(
                new Coding()
                        .setSystem(TransportConstants.SNOMED_SYSTEMS)
                        .setCode(TransportConstants.CODE_PROCEDURE)
                        .setDisplay(TransportConstants.CODE_PROCEDURE_DISPLAY));
    }
    private static CodeableConcept getServiceCategory(){
        return new CodeableConcept().addCoding(
                new Coding()
                        .setSystem(TransportConstants.HL7_SYSTEMS_SERVICE_CATEGORY)
                        .setCode(TransportConstants.SERVICE_CATEGORY)
                        .setDisplay(TransportConstants.SERVICE_CATEGORY_DISPLAY));
    }

    private static CodeableConcept getServiceType(){
        return new CodeableConcept().addCoding(
                new Coding()
                        .setSystem(TransportConstants.HL7_SYSTEMS_SERVICE_TYPE)
                        .setCode(TransportConstants.SERVICE_TYPE)
                        .setDisplay(TransportConstants.SERVICE_TYPE_DISPLAY));
    }

    private static CodeableConcept getOperatorParticipationType(){
        return new CodeableConcept().addCoding(
                new Coding()
                        .setCode(TransportConstants.ENCOUNTER_OPERATOR_PARTICIPATION_TYPE)
                        .setSystem(TransportConstants.HL7_SYSTEMS_PARTICIPATION_TYPE));
    }

    private static CodeableConcept getAmbulanceParticipationType(){
        return new CodeableConcept().addCoding(
                new Coding()
                        .setCode(TransportConstants.ENCOUNTER_AMBULANCE_PARTICIPATION_TYPE)
                        .setSystem(TransportConstants.HL7_SYSTEMS_PARTICIPATION_TYPE));
    }

    private static Location getDepartureRoute(@NotNull TransportRoute route){
        return new Location()
                .setName("departure")
                .setAddress(new Address()
                        .addLine(route.getDeparture().getAddress().getAddress())
                        .setCity(route.getDeparture().getCity().getCity())
                        .setDistrict(route.getDeparture().getDistrict().getDistrict())
                        .setPostalCode(String.valueOf(route.getDeparture().getPostalCode().getPostalCode())));
    }

    private static Location getDestinationRoute(@NotNull TransportRoute route){
        return new Location()
                .setName("destination")
                .setAddress(new Address()
                        .addLine(route.getDeparture().getAddress().getAddress())
                        .setCity(route.getDeparture().getCity().getCity())
                        .setDistrict(route.getDeparture().getDistrict().getDistrict())
                        .setPostalCode(String.valueOf(route.getDeparture().getPostalCode().getPostalCode())));
    }
}
