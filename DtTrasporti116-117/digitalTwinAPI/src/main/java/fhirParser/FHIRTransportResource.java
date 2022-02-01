/*
 * Copyright (c) 2022. Giada Gibertoni
 */

package fhirParser;

import dtModel.patient.PatientDtModel;
import dtModel.transport.TransportDtModel;
import dtModel.transport.TransportRoute;
import dtModel.vehicle.ambulance.AmbulanceDtModel;
import dtModel.vehicle.operator.OperatorDtModel;
import org.hl7.fhir.r4.model.*;
import org.jetbrains.annotations.NotNull;
import utils.Constants;

import java.time.ZoneId;
import java.util.Date;
import java.util.List;

public class FHIRTransportResource {
    public static String createTransportAppointmentFHIRResource(@NotNull TransportDtModel dtTransport, @NotNull AmbulanceDtModel dtAmbulance, @NotNull PatientDtModel dtPatient) {
        DomainResource transport = new Appointment()
                .addIdentifier(new Identifier().setValue(dtTransport.getId() + Constants.APPOINTMENT))
                .addServiceCategory(getServiceCategory())
                .addServiceType(getServiceType())
                .setStart(Date.from(dtTransport.getStartDateTime().atZone(ZoneId.systemDefault()).toInstant()))
                .setParticipant(List.of(
                        new Appointment.AppointmentParticipantComponent()
                                .setActor(getPatientRef(dtPatient))
                                .setStatus(Appointment.ParticipationStatus.ACCEPTED),
                        new Appointment.AppointmentParticipantComponent()
                                .setActor(getAmbulanceReference(dtAmbulance))
                                .setStatus(Appointment.ParticipationStatus.ACCEPTED)))
                .setContained(List.of(getDepartureRoute(dtTransport.getRoute()), getDestinationRoute(dtTransport.getRoute())));

        return FHIRParser.getParser().encodeResourceToString(transport);
    }

    public static String createTransportEncounterFHIRResource(@NotNull TransportDtModel dtTransport, @NotNull AmbulanceDtModel dtAmbulance, @NotNull PatientDtModel dtPatient, @NotNull OperatorDtModel dtOperator) {
        Procedure transportProcedure = new Procedure()
                .addIdentifier(new Identifier().setValue(dtTransport.getId() + Constants.PROCEDURE))

                .setCode(getProcedureCode())
                .setSubject(getPatientRef(dtPatient))
                .setPerformer(List.of(
                        new Procedure.ProcedurePerformerComponent().setActor(getOperatorReference(dtOperator)),
                        new Procedure.ProcedurePerformerComponent().setActor(getAmbulanceReference(dtAmbulance))))
                .setEncounter(new Reference().setReference(dtTransport.getId() + Constants.ENCOUNTER));

        Encounter transportEncounter = new Encounter()
                .addIdentifier(new Identifier().setValue(dtTransport.getId() + Constants.ENCOUNTER))
                .setClass_(new Coding().setCode(Constants.ENCOUNTER_ACT_CODE).setSystem(Constants.HL7_SYSTEMS_ACT_CODE))
                .setSubject(getPatientRef(dtPatient))
                .setServiceType(getServiceType())
                .setParticipant(List.of(
                        new Encounter.EncounterParticipantComponent()
                                .addType(getOperatorParticipationType())
                                .setIndividual(getOperatorReference(dtOperator)),
                        new Encounter.EncounterParticipantComponent()
                                .addType(getAmbulanceParticipationType())
                                .setIndividual(getAmbulanceReference(dtAmbulance))))
                .addAppointment(new Reference().setReference(dtTransport.getId() + Constants.APPOINTMENT))
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
                return createTransportAppointmentFHIRResource(dtTransport, dtAmbulance, dtPatient);
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

    private static Reference getPatientRef(@NotNull PatientDtModel dtPatient){
        return new Reference().setReference(Constants.PATIENT_REF + dtPatient.getPersonalData().getFiscalCode().getFiscalCode())
                .setDisplay(dtPatient.getPersonalData().getName() + " " + dtPatient.getPersonalData().getSurname());
    }

    private static Reference getOperatorReference(@NotNull OperatorDtModel dtOperator){
        return new Reference().setReference(Constants.PRACTITIONER_REF + dtOperator.getOperatorId())
                .setDisplay(dtOperator.getPersonalData().getName() + " " + dtOperator.getPersonalData().getSurname());
    }

    private static Reference getAmbulanceReference(@NotNull AmbulanceDtModel dtAmbulance){
        return new Reference().setReference(Constants.DEVICE_REF + dtAmbulance.getId());
    }

    private static CodeableConcept getProcedureCode(){
        return new CodeableConcept().addCoding(
                new Coding()
                        .setSystem(Constants.SNOMED_SYSTEMS)
                        .setCode(Constants.CODE_PROCEDURE)
                        .setDisplay(Constants.CODE_PROCEDURE_DISPLAY));
    }
    private static CodeableConcept getServiceCategory(){
        return new CodeableConcept().addCoding(
                new Coding()
                        .setSystem(Constants.HL7_SYSTEMS_SERVICE_CATEGORY)
                        .setCode(Constants.SERVICE_CATEGORY)
                        .setDisplay(Constants.SERVICE_CATEGORY_DISPLAY));
    }

    private static CodeableConcept getServiceType(){
        return new CodeableConcept().addCoding(
                new Coding()
                        .setSystem(Constants.HL7_SYSTEMS_SERVICE_TYPE)
                        .setCode(Constants.SERVICE_TYPE)
                        .setDisplay(Constants.SERVICE_TYPE_DISPLAY));
    }

    private static CodeableConcept getOperatorParticipationType(){
        return new CodeableConcept().addCoding(
                new Coding()
                        .setCode(Constants.ENCOUNTER_OPERATOR_PARTICIPATION_TYPE)
                        .setSystem(Constants.HL7_SYSTEMS_PARTICIPATION_TYPE));
    }

    private static CodeableConcept getAmbulanceParticipationType(){
        return new CodeableConcept().addCoding(
                new Coding()
                        .setCode(Constants.ENCOUNTER_AMBULANCE_PARTICIPATION_TYPE)
                        .setSystem(Constants.HL7_SYSTEMS_PARTICIPATION_TYPE));
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