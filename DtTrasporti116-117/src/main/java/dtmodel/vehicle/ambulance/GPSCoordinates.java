/*
 * Copyright (c) 2022. Giada Gibertoni
 */

package dtmodel.vehicle.ambulance;

import com.azure.core.annotation.Fluent;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Represents the earth's coordinates
 * */
@Fluent
@JsonInclude(JsonInclude.Include.NON_NULL)
public class GPSCoordinates {
    @JsonProperty(value = "longitude")
    private double longitude;
    @JsonProperty(value = "latitude")
    private double latitude;

    /**
     * @return longitude
     */
    public double getLongitude() {
        return longitude;
    }

    /**
     * @return latitude
     */
    public double getLatitude() {
        return latitude;
    }

    /**
     * @param longitude of ambulance
     *
     * @return GPSCoordinates
     */
    public GPSCoordinates setLongitude(double longitude) {
        this.longitude = longitude;
        return this;
    }

    /**
     * @param latitude of ambulance
     *
     * @return GPSCoordinates
     */
    public GPSCoordinates setLatitude(double latitude) {
        this.latitude = latitude;
        return this;
    }
}