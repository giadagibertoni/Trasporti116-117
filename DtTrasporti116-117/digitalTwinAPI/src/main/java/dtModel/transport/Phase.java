/*
 * Copyright (c) 2022. Giada Gibertoni
 */

package dtModel.transport;

import com.azure.core.annotation.Fluent;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonValue;

@Fluent
@JsonInclude(JsonInclude.Include.NON_NULL)
public enum Phase {
    /**
     * Transport is scheduled
     */
    @JsonProperty(value = "scheduled")
    SCHEDULED("scheduled"),
    /**
     * Transport is in progress
     */
    @JsonProperty(value = "InProgress")
    IN_PROGRESS("InProgress"),
    /**
     * Transport is completed
     */
    @JsonProperty(value = "completed")
    COMPLETED("completed");

    private final String value;

    /**
     * Transport's phase
     *
     * @param v phase
     */
    Phase(final String v) {
        this.value = v;
    }

    /**
     * @return transport's phase
     */
    @JsonValue
    public String getValue() {
        return this.value;
    }

}
