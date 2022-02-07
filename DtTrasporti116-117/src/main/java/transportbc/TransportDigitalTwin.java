/*
 * Copyright (c) 2022. Giada Gibertoni
 */

package transportbc;

import com.azure.core.http.rest.PagedIterable;
import com.azure.digitaltwins.core.BasicDigitalTwin;
import com.azure.digitaltwins.core.BasicDigitalTwinMetadata;
import com.azure.digitaltwins.core.BasicRelationship;
import sharedkernel.azureservice.Client;
import org.hl7.fhir.r4.model.Appointment;
import org.hl7.fhir.r4.model.Location;
import org.json.simple.JSONObject;
import transportbc.dtmodel.*;

import java.time.ZoneId;
import java.util.ArrayList;
import java.util.List;

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
                .setPhase(Phase.SCHEDULED);

        BasicDigitalTwin transportDT = new BasicDigitalTwin(dt.getId())
                .setMetadata(
                        new BasicDigitalTwinMetadata().setModelId(TransportConstants.TRANSPORT_MODEL_ID)
                )
                .addToContents("route", dt.getRoute())
                .addToContents("phase", dt.getPhase())
                .addToContents("startDateTime", dt.getStartDateTime());

        BasicDigitalTwin basicTwinResponse = Client.getClient().createOrReplaceDigitalTwin(dt.getId(), transportDT, BasicDigitalTwin.class);

        addPatientRel(transport.getParticipant().get(0).getActor().getReference().replace(TransportConstants.PATIENT_REF, ""), id);
        addAmbulanceRel(transport.getParticipant().get(1).getActor().getReference().replace(TransportConstants.DEVICE_REF, ""), id);


        return basicTwinResponse.getId();
    }

    /**
     * Get all scheduled transport
     *
     * @return List of transport resource
     */
    public static List<String> getScheduledTransports() {
        List<String> transports = new ArrayList<>();
        String query = "SELECT * FROM DIGITALTWINS T WHERE IS_OF_MODEL('" + TransportConstants.TRANSPORT_MODEL_ID + "') " +
                "AND T.phase = 'scheduled'";
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

            transports.add(FHIRTransportResource.createTransportAppointmentFHIRResource(dtTransport, ambulanceId, patientId));
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
