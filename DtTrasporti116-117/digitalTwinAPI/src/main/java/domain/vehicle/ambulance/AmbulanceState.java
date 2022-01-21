/*
 * Copyright (c) 2022. Giada Gibertoni
 */

package domain.vehicle.ambulance;

public enum AmbulanceState {
    /**
     * Ambulance is busy
     */
    BUSY(1),
    /**
     * Ambulance is free
     */
    FREE(2),
    /**
     * Ambulance is under maintenance
     */
    UNDER_MAINTENANCE(3),
    /**
     * Ambulance is disused
     */
    DISUSED(4);

    private final int value;

    /**
     * Ambulance's state
     *
     * @param v state
     */
    AmbulanceState(final int v) {
        this.value = v;
    }

    /**
     * @return ambulance's state
     */
    public int getValue() {
        return this.value;
    }

}
