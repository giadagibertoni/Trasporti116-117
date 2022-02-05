/*
 * Copyright (c) 2022. Giada Gibertoni
 */

package dtmodel.transport;

import com.azure.core.annotation.Fluent;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Class that represent the transport route
 */
@Fluent
@JsonInclude(JsonInclude.Include.NON_NULL)
public class TransportRoute {
    @JsonProperty(value = "departure")
    private TransportLocation departure;
    @JsonProperty(value = "destination")
    private TransportLocation destination;

    /**
     * @return departure location
     */
    public TransportLocation getDeparture() {
        return this.departure;
    }

    /**
     * @return destination location
     */
    public TransportLocation getDestination() {
        return this.destination;
    }

    /**
     *
     * @param departure of transport
     *
     * @return TransportRoute
     */
    public TransportRoute setDeparture(TransportLocation departure) {
        this.departure = departure;
        return this;
    }

    /**
     *
     * @param destination of transport
     *
     * @return TransportRoute
     */
    public TransportRoute setDestination(TransportLocation destination) {
        this.destination = destination;
        return this;
    }
}

