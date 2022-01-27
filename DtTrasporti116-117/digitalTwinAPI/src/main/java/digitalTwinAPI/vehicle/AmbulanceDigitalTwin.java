/*
 * Copyright (c) 2022. Giada Gibertoni
 */

package digitalTwinAPI.vehicle;

import com.azure.core.http.rest.PagedIterable;
import com.azure.digitaltwins.core.BasicDigitalTwin;
import com.azure.digitaltwins.core.BasicDigitalTwinMetadata;
import digitalTwinAPI.connection.Client;
import dtModel.vehicle.ambulance.AmbulanceDtModel;
import dtModel.vehicle.ambulance.AmbulanceId;
import dtModel.vehicle.ambulance.AmbulanceState;
import dtModel.vehicle.ambulance.GPSCoordinates;
import fhirParser.FHIRAmbulanceResource;
import org.hl7.fhir.r4.model.Device;
import org.hl7.fhir.r4.model.Location;
import utils.Constants;

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
        AmbulanceId id = new AmbulanceId(ambulance.getIdentifierFirstRep().getValue());

        AmbulanceState state;
        switch(ambulance.getStatus()){
            case ACTIVE -> state = AmbulanceState.BUSY;
            case INACTIVE -> state = AmbulanceState.FREE;
            case ENTEREDINERROR -> state = AmbulanceState.UNDER_MAINTENANCE;
            default -> state = AmbulanceState.DISUSED;
        }

        Location location = (Location) ambulance.getContained().get(0);
        GPSCoordinates coordinates =  new GPSCoordinates(
                location.getPosition().getLongitude().doubleValue(),
                location.getPosition().getLatitude().doubleValue());

        BasicDigitalTwin ambulanceDT = new BasicDigitalTwin(id.getid())
                .setMetadata(
                        new BasicDigitalTwinMetadata().setModelId(Constants.AMBULANCE_MODEL_ID)
                )
                .addToContents("id", id)
                .addToContents("state", state.getValue())
                .addToContents("coordinates", coordinates);

        BasicDigitalTwin basicTwinResponse = Client.getClient().createOrReplaceDigitalTwin(id.getid(), ambulanceDT, BasicDigitalTwin.class);

        return basicTwinResponse.getId();
    }


    /**
     * Get all ambulance
     *
     * @return List of ambulance resource
     */
    public static List<String> getAmbulance() {
        List<String> ambulances = new ArrayList<>();
        String query = "SELECT * FROM DIGITALTWINS WHERE IS_OF_MODEL('" + Constants.AMBULANCE_MODEL_ID + "')";
        PagedIterable<AmbulanceDtModel> pageableResponse = Client.getClient().query(query, AmbulanceDtModel.class);

        pageableResponse.forEach(dt -> ambulances.add(FHIRAmbulanceResource.createFHIRResource(dt)));

        return ambulances;
    }
}
