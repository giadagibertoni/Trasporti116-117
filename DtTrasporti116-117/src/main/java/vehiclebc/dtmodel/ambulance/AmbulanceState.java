/*
 * Copyright (c) 2022. Giada Gibertoni
 */

package vehiclebc.dtmodel.ambulance;
import com.azure.core.annotation.Fluent;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonValue;

@Fluent
@JsonInclude(JsonInclude.Include.NON_NULL)
public enum AmbulanceState {
    /**
     * Ambulance is busy
     */
    @JsonProperty(value = "busy")
    BUSY("busy"),

    /**
     * Ambulance is free
     */
    @JsonProperty(value = "free")
    FREE("free"),

    /**
     * Ambulance is under maintenance
     */
    @JsonProperty(value = "underMaintenance")
    UNDER_MAINTENANCE("underMaintenance"),

    /*
     * Ambulance is disused
     */
    @JsonProperty(value = "disused")
    DISUSED("disused");

    private final String value;

    /**
     * Ambulance's state
     *
     * @param v state
     */
    AmbulanceState(final String v) {
        this.value = v;
    }

    /**
     * @return ambulance's state
     */
    @JsonValue
    public String getValue() {
        return this.value;
    }

}
