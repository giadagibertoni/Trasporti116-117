/*
 * Copyright (c) 2022. Giada Gibertoni
 */

package transportbc;

import com.azure.core.http.rest.PagedIterable;
import com.azure.core.models.JsonPatchDocument;
import com.azure.digitaltwins.core.BasicDigitalTwin;
import com.azure.digitaltwins.core.BasicDigitalTwinMetadata;
import com.azure.digitaltwins.core.BasicRelationship;
import sharedkernel.azureservice.Client;
import org.hl7.fhir.r4.model.Appointment;
import org.hl7.fhir.r4.model.Location;
import org.json.simple.JSONObject;
import transportbc.dtmodel.*;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class TransportDigitalTwin {

    /**
     * Create a transport digital twin
     *
     * @return id of the transport created
     */
    public static String createTransport(String resource) {
        Appointment transport;
        if (resource.contains("appointment")){
            transport = FHIRTransportResource.parseAppointmentFHIRResource(resource);
        }else {
            throw new IllegalStateException();
        }
        String id = transport.getIdentifierFirstRep().getValue().replace(TransportConstants.APPOINTMENT, "");

        Location departure = (Location) transport.getContained().get(0);
        Location destination = (Location) transport.getContained().get(1);

        TransportDtModel dt = new TransportDtModel()
                .setRoute(new TransportRoute()
                        .setDestination(new TransportLocation()
                                .setAddress(new TransportAddress().setAddress(departure.getAddress().getLine().get(0).toString()))
                                .setCity(new TransportCity().setCity(departure.getAddress().getCity()))
                                .setDistrict(new TransportDistrict().setDistrict(departure.getAddress().getDistrict()))
                                .setPostalCode(new TransportPostalCode().setPostalCode(Integer.parseInt(departure.getAddress().getPostalCode()))))
                        .setDeparture(new TransportLocation()
                                .setAddress(new TransportAddress().setAddress(destination.getAddress().getLine().get(0).toString()))
                                .setCity(new TransportCity().setCity(destination.getAddress().getCity()))
                                .setDistrict(new TransportDistrict().setDistrict(destination.getAddress().getDistrict()))
                                .setPostalCode(new TransportPostalCode().setPostalCode(Integer.parseInt(destination.getAddress().getPostalCode())))))
                .setId(id)
                .setStartDateTime(transport.getStart().toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime())
                .setEndDateTime(transport.getEnd().toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime())
                .setPhase(Phase.SCHEDULED);

        BasicDigitalTwin transportDT = new BasicDigitalTwin(dt.getId())
                .setMetadata(
                        new BasicDigitalTwinMetadata().setModelId(TransportConstants.TRANSPORT_MODEL_ID)
                )
                .addToContents("route", dt.getRoute())
                .addToContents("phase", dt.getPhase())
                .addToContents("startDateTime", dt.getStartDateTime())
                .addToContents("endDateTime", dt.getEndDateTime());

        BasicDigitalTwin basicTwinResponse = Client.getClient().createOrReplaceDigitalTwin(dt.getId(), transportDT, BasicDigitalTwin.class);

        addPatientRel(transport.getParticipant().get(0).getActor().getReference().replace(TransportConstants.PATIENT_REF, ""), id);
        addAmbulanceRel(transport.getParticipant().get(1).getActor().getReference().replace(TransportConstants.DEVICE_REF, ""), id);


        return basicTwinResponse.getId();
    }

    public static void setTransportInProgress (String idTransport){
        Client.getClient().updateDigitalTwin(idTransport,
                new JsonPatchDocument()
                        .appendAdd("/phase", Phase.IN_PROGRESS.getValue())
                        .appendAdd("/startDateTime", LocalDateTime.now()));
    }

    public static void setTransportCompleted (String idTransport){
        Client.getClient().updateDigitalTwin(idTransport,
                new JsonPatchDocument()
                        .appendAdd("/phase", Phase.COMPLETED.getValue())
                        .appendAdd("/endDateTime", LocalDateTime.now()));
    }

    public static void setTransportDeleted (String idTransport){
        Client.getClient().updateDigitalTwin(idTransport,
                new JsonPatchDocument().appendAdd("/phase", Phase.CANCELLED.getValue()));
    }

    /**
     * Get all scheduled transport
     *
     * @return List of transport resource
     */
    public static List<String> getScheduledTransports() {
        return getTransport(Phase.SCHEDULED);
    }

    /**
     * Get all cancelled transport
     *
     * @return List of transport resource
     */
    public static List<String> getCancelledTransports() {
        return getTransport(Phase.CANCELLED);
    }

    /**
     * Get all transport in process
     *
     * @return List of transport resource
     */
    public static List<String> getInProgressTransports() {
        return getTransport(Phase.IN_PROGRESS);
    }

    /**
     * Get all completed transport
     *
     * @return List of transport resource
     */
    public static List<String> getCompletedTransports() {
        return getTransport(Phase.COMPLETED);
    }

    /**
     * Get all completed transport
     *
     * @return List of transport resource
     */
    public static List<String> getDeletedTransports() {
        return getTransport(Phase.CANCELLED);
    }

    private static List<String> getTransport(Phase phase){
        List<String> transports = new ArrayList<>();
        String query = "SELECT * FROM DIGITALTWINS T WHERE IS_OF_MODEL('" + TransportConstants.TRANSPORT_MODEL_ID + "') " +
                "AND T.phase = '" + phase.getValue() + "'";

        PagedIterable<TransportDtModel> pageableResponse = Client.getClient().query(query, TransportDtModel.class);

        pageableResponse.forEach(dtTransport -> {
            String relAmbulanceQuery = "SELECT A.$dtId FROM DIGITALTWINS T JOIN A RELATED T.use WHERE T.$dtId = '"+ dtTransport.getId() +"'";
            PagedIterable<JSONObject> transportAmbulanceRelationship = Client.getClient().query(relAmbulanceQuery, JSONObject.class);

            String relPatientQuery = "SELECT P.$dtId FROM DIGITALTWINS T JOIN P RELATED T.transport WHERE T.$dtId = '"+ dtTransport.getId() +"'";
            PagedIterable<JSONObject> transportPatientRelationship = Client.getClient().query(relPatientQuery, JSONObject.class);

            String ambulanceId;
            String patientId;

            if (transportAmbulanceRelationship.stream().findFirst().isPresent() &&
                    transportPatientRelationship.stream().findFirst().isPresent()) {
                ambulanceId = transportAmbulanceRelationship.stream().findFirst().get().get("$dtId").toString();
                patientId = transportPatientRelationship.stream().findFirst().get().get("$dtId").toString();
            } else
                throw new IllegalStateException();

            if (phase.equals(Phase.SCHEDULED) || phase.equals(Phase.CANCELLED))
                transports.add(FHIRTransportResource.createTransportAppointmentFHIRResource(dtTransport, ambulanceId, patientId));
            else{
                String relOperatorQuery = "SELECT P.$dtId FROM DIGITALTWINS T JOIN P RELATED T.driveBy WHERE T.$dtId = '"+ ambulanceId +"'";
                Optional<JSONObject> operatorId = Client.getClient().query(relOperatorQuery, JSONObject.class).stream().findFirst();

                if (operatorId.stream().findFirst().isPresent())
                    transports.add(FHIRTransportResource
                            .createTransportEncounterFHIRResource(dtTransport, ambulanceId, patientId, operatorId.get().get("$dtId").toString()));
                else
                    throw new IllegalStateException();
            }
        });

        return transports;
    }

    private static void addPatientRel(String patientId, String transportId){
        String relationshipId = transportId + "for" + patientId;
        Client.getClient().createOrReplaceRelationship(
                transportId,
                relationshipId,
                new BasicRelationship(
                        relationshipId,
                        transportId,
                        patientId,
                        TransportConstants.TRANSPORT_PATIENT_REL
                ),
                BasicRelationship.class
        );
    }

    private static void addAmbulanceRel(String ambulanceId, String transportId){
        String relationshipId = transportId + "for" + ambulanceId;
        Client.getClient().createOrReplaceRelationship(
                transportId,
                relationshipId,
                new BasicRelationship(
                        relationshipId,
                        transportId,
                        ambulanceId,
                        TransportConstants.TRANSPORT_AMBULANCE_REL
                ),
                BasicRelationship.class
        );
    }
}
