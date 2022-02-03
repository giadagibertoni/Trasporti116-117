/*
 * Copyright (c) 2022. Giada Gibertoni
 */

package digitalTwinAPI.vehicle;

import com.azure.core.http.rest.PagedIterable;
import com.azure.digitaltwins.core.BasicDigitalTwin;
import com.azure.digitaltwins.core.BasicDigitalTwinMetadata;
import com.azure.digitaltwins.core.BasicRelationship;
import digitalTwinAPI.connection.Client;
import digitalTwinAPI.connection.IoTHubDevice;
import dtModel.vehicle.ambulance.AmbulanceDtModel;
import dtModel.vehicle.ambulance.AmbulanceState;
import dtModel.vehicle.ambulance.GPSCoordinates;
import fhirSerializer.FHIRAmbulanceResource;
import org.hl7.fhir.r4.model.Device;
import org.hl7.fhir.r4.model.Location;
import utils.Constants;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class AmbulanceDigitalTwin {
    /**
     * Create a ambulance digital twin
     *
     * @return id of the ambulance created
     */
    public static String createAmbulance(String resource) {
        Device ambulance = FHIRAmbulanceResource.parseFHIRResource(resource);
        String id = ambulance.getIdentifierFirstRep().getValue();

        AmbulanceState state;
        switch(ambulance.getStatus()){
            case ACTIVE -> state = AmbulanceState.BUSY;
            case INACTIVE -> state = AmbulanceState.FREE;
            case ENTEREDINERROR ->
                state = AmbulanceState.UNDER_MAINTENANCE;
            default -> state = AmbulanceState.DISUSED;
        }

        Location location = (Location) ambulance.getContained().get(0);
        GPSCoordinates coordinates =  new GPSCoordinates()
                .setLongitude(location.getPosition().getLongitude().doubleValue())
                .setLatitude(location.getPosition().getLatitude().doubleValue());

        BasicDigitalTwin ambulanceDT = new BasicDigitalTwin(id)
                .setMetadata(
                        new BasicDigitalTwinMetadata().setModelId(Constants.AMBULANCE_MODEL_ID)
                )
                .addToContents("state", state.getValue())
                .addToContents("coordinates", coordinates);

        BasicDigitalTwin basicTwinResponse =
                Client.getClient().createOrReplaceDigitalTwin(id, ambulanceDT, BasicDigitalTwin.class);

        try {
            IoTHubDevice.addAmbulanceGPSDevice(id);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return basicTwinResponse.getId();
    }

    public static void addOperatorWorkDay(String ambulanceId, String operatorId, LocalDate date){
        String relationshipId = ambulanceId + "DriveBy" + operatorId;
        Client.getClient().createOrReplaceRelationship(
                ambulanceId,
                relationshipId,
                new BasicRelationship(
                        relationshipId,
                        ambulanceId,
                        operatorId,
                        Constants.AMBULANCE_OPERATOR_REL
                )
                .addProperty("workDate", date),
                BasicRelationship.class
        );
    }

    /**
     * Get all ambulance
     *
     * @return List of ambulances resource
     */
    public static List<String> getAmbulances() {
        List<String> ambulances = new ArrayList<>();
        String query = "SELECT * FROM DIGITALTWINS " +
                "WHERE IS_OF_MODEL('"
                + Constants.AMBULANCE_MODEL_ID + "')";
        PagedIterable<AmbulanceDtModel> response =
                Client.getClient()
                        .query(query, AmbulanceDtModel.class);
        response.forEach(dt -> ambulances.add(
                FHIRAmbulanceResource.createFHIRResource(dt)));
        return ambulances;
    }
}
