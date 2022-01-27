/*
 * Copyright (c) 2022. Giada Gibertoni
 */

package dtModel.transport;

import com.azure.core.annotation.Fluent;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Class that represents the transport identifier
 */
@Fluent
@JsonInclude(JsonInclude.Include.NON_NULL)
public class TransportId {
    @JsonProperty(value = "id")
    private String id;

    /**
     * @return transport identifier
     */
    public String getId() {
        return id;
    }

    /**
     *
     * @param id of transport
     *
     * @return TransportId
     */
    public TransportId setId(String id) {
        this.id = id;
        return this;
    }
}
