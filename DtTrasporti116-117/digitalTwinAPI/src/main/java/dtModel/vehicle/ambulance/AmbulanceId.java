/*
 * Copyright (c) 2022. Giada Gibertoni
 */

package dtModel.vehicle.ambulance;

import com.azure.core.annotation.Fluent;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Represents the id of ambulance
 * */

@Fluent
@JsonInclude(JsonInclude.Include.NON_NULL)
public class AmbulanceId {
    @JsonProperty(value = "id")
    private String id;

    /**
     * @return id
     */
    public String getid() {
        return id;
    }

    /**
     * @param id of ambulance
     *
     * @return AmbulanceId
     */
    public AmbulanceId setId(String id) {
        this.id = id;
        return this;
    }

}