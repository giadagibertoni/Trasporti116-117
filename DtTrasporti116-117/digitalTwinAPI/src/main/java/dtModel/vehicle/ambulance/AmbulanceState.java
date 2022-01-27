/*
 * Copyright (c) 2022. Giada Gibertoni
 */

package dtModel.vehicle.ambulance;

import com.fasterxml.jackson.annotation.JsonProperty;

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
    /*
     * Ambulance is disused
     */

    DISUSED(4);

    private int value;

    AmbulanceState(){
    }
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
