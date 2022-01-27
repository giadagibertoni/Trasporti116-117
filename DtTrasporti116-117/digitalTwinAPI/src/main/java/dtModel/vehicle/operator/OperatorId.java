/*
 * Copyright (c) 2022. Giada Gibertoni
 */

package dtModel.vehicle.operator;

import com.azure.core.annotation.Fluent;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Class that represents the operator identifier
 */
@Fluent
@JsonInclude(JsonInclude.Include.NON_NULL)
public class OperatorId {
    @JsonProperty(value = "id")
    private String id;

    /**
     * @return operator identifier
     */
    public String getId() {
        return this.id;
    }

    /**
     *
     * @param id of operator
     *
     * @return OperatorId
     */
    public OperatorId setId(String id) {
        this.id = id;
        return this;
    }


}