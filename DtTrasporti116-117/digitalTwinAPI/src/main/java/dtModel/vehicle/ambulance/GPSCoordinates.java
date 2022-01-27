/*
 * Copyright (c) 2022. Giada Gibertoni
 */

package dtModel.vehicle.ambulance;

/**
 * Represents the earth's coordinates
 * */
public class GPSCoordinates {
    private double longitude;
    private double latitude;

    public GPSCoordinates() {
        super();
    }
    /**
     * Create the coordinates
     *
     * @param lat latitude of coordinates
     * @param lon longitude of coordinates
     */
    public GPSCoordinates(final double lon, final double lat) {
        this.longitude = lon;
        this.latitude = lat;
    }

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
}