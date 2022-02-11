/*
 * Copyright (c) 2022. Giada Gibertoni
 */

package vehiclebc;

import com.azure.core.http.rest.PagedIterable;
import com.azure.digitaltwins.core.BasicDigitalTwin;
import com.azure.digitaltwins.core.BasicDigitalTwinMetadata;
import com.azure.digitaltwins.core.BasicRelationship;
import sharedkernel.azureservice.Client;
import sharedkernel.azureservice.IoTHubDevice;
import vehiclebc.dtmodel.ambulance.AmbulanceDtModel;
import vehiclebc.dtmodel.ambulance.AmbulanceState;
import vehiclebc.dtmodel.ambulance.GPSCoordinates;
import org.hl7.fhir.r4.model.Device;
import org.hl7.fhir.r4.model.Location;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

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
                        new BasicDigitalTwinMetadata().setModelId(VehicleConstants.AMBULANCE_MODEL_ID)
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
                        VehicleConstants.AMBULANCE_OPERATOR_REL
                )
                .addProperty("workDate", date),
                BasicRelationship.class
        );
    }

    /**
     * Get ambulance by id
     *
     * @return ambulance resource
     */
    public static Optional<String> getAmbulance(String id) {
        String query = "SELECT * FROM DIGITALTWINS " +
                "WHERE IS_OF_MODEL('"
                + VehicleConstants.AMBULANCE_MODEL_ID + "' )"
                + "AND $dtId = '" + id + "'";

        Optional<AmbulanceDtModel> dt = Client.getClient().query(query, AmbulanceDtModel.class).stream().findFirst();

        return dt.map(FHIRAmbulanceResource::createFHIRResource);
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
                + VehicleConstants.AMBULANCE_MODEL_ID + "')";
        PagedIterable<AmbulanceDtModel> response =
                Client.getClient()
                        .query(query, AmbulanceDtModel.class);
        response.forEach(dt -> ambulances.add(
                FHIRAmbulanceResource.createFHIRResource(dt)));
        return ambulances;
    }

    public static Optional<String> getFreeAmbulance(LocalDateTime startDateTime,  LocalDateTime endDateTime) {
        String getAmbulance = "SELECT * FROM DIGITALTWINS " + "WHERE IS_OF_MODEL('" + VehicleConstants.AMBULANCE_MODEL_ID + "')";

        Optional<AmbulanceDtModel> ambulance = Client.getClient().query(getAmbulance, AmbulanceDtModel.class).stream().filter(a -> {

            String getFreeAmbulance = "SELECT A.$dtId FROM DIGITALTWINS T JOIN A RELATED T.use " +
                    "WHERE A.$dtId= '" + a.getId() + "' " +
                    "AND NOT ((T.startDateTime >= '" + startDateTime +
                    "' AND T.startDateTime <= '" + endDateTime +
                    "') OR (T.endDateTime >= '" + startDateTime +
                    "' AND T.endDateTime <= '" + endDateTime + "'))";

            return Client.getClient().query(getFreeAmbulance, AmbulanceDtModel.class).stream().count() > 0;
        }).findFirst();

        return ambulance.map(FHIRAmbulanceResource::createFHIRResource);
    }


}
