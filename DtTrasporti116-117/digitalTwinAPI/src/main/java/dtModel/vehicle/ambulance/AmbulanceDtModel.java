/*
 * Copyright (c) 2022. Giada Gibertoni
 */

package dtModel.vehicle.ambulance;

import com.fasterxml.jackson.annotation.JsonCreator;

import static com.fasterxml.jackson.annotation.JsonCreator.Mode.DEFAULT;

/**
 * Class for deserializing a ambulance digital twin.
 */
public class AmbulanceDtModel {
    private AmbulanceId id;
    private AmbulanceState state;
    private GPSCoordinates coordinates;

    public AmbulanceDtModel() {super();}

    /**
     * Ambulance's Digital Twin model
     *
     * @param i ambulance id
     * @param s ambulance state
     * @param c gps coordinates
     */

    public AmbulanceDtModel(AmbulanceId i, AmbulanceState s, GPSCoordinates c) {
        this.id = i;
        this.state = s;
        this.coordinates = c;
    }

    /**
     * Return the ambulance id
     *
     * @return ambulance id
     */
    public AmbulanceId getId() {
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

}
