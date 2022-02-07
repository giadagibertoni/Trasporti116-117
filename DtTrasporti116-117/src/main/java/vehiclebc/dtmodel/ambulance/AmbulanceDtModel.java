/*
 * Copyright (c) 2022. Giada Gibertoni
 */

package vehiclebc.dtmodel.ambulance;

import com.azure.digitaltwins.core.models.DigitalTwinsJsonPropertyNames;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Class for serializing and deserializing a ambulance digital twin.
 */
public class AmbulanceDtModel {
    @JsonProperty(value = DigitalTwinsJsonPropertyNames.DIGITAL_TWIN_ID, required = true)
    private String id;
    @JsonProperty(value = "state")
    private AmbulanceState state;
    @JsonProperty(value = "coordinates")
    private GPSCoordinates coordinates;

    /**
     * Return the ambulance id
     *
     * @return ambulance id
     */
    public String getId() {
        return id;
    }

    /**
     * Return the ambulance's state
     *
     * @return ambulance's state
     */
    public AmbulanceState getState() {
        return state;
    }

    /**
     * Return the ambulance's coordinates
     *
     * @return ambulance's coordinates
     */
    public GPSCoordinates getCoordinates() {
        return coordinates;
    }

    /**
     * @param id of ambulance
     *
     * @return AmbulanceDtModel
     */
    public AmbulanceDtModel setId(String id) {
        this.id = id;
        return this;
    }

    /**
     * @param state of ambulance
     *
     * @return AmbulanceDtModel
     */
    public AmbulanceDtModel setState(AmbulanceState state) {
        this.state = state;
        return this;
    }

    /**
     * @param coordinates of ambulance
     *
     * @return AmbulanceDtModel
     */
    public AmbulanceDtModel setCoordinates(GPSCoordinates coordinates) {
        this.coordinates = coordinates;
        return this;
    }

}
