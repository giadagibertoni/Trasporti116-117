/*
 * Copyright (c) 2022. Giada Gibertoni
 */

package dtModel.vehicle.ambulance;

/**
 * Class for serializing and deserializing a ambulance digital twin.
 */
public class AmbulanceDtModel {
    private AmbulanceId id;
    private AmbulanceState state;
    private GPSCoordinates coordinates;

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

    /**
     * @param id of ambulance
     *
     * @return AmbulanceDtModel
     */
    public AmbulanceDtModel setId(AmbulanceId id) {
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
