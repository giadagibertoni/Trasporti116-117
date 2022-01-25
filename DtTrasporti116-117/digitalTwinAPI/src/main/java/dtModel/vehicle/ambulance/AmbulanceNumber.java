/*
 * Copyright (c) 2022. Giada Gibertoni
 */

package dtModel.vehicle.ambulance;

/**
 * Represents the ambulance's number
 */
public class AmbulanceNumber {
    private final Integer number;

    /**
     * Ambulance's number
     *
     * @param n number
     */
    public AmbulanceNumber(final Integer n) {
        this.number = n;
    }

    /**
     * Return the ambulance's number
     *
     * @return ambulance number
     */
    public Integer getAmbulanceNumber() {
        return this.number;
    }
}
