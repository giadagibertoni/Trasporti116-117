/*
 * Copyright (c) 2022. Giada Gibertoni
 */

package dtModel.transport;

import com.azure.core.annotation.Fluent;
import com.azure.digitaltwins.core.models.DigitalTwinsJsonPropertyNames;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.time.LocalDateTime;

@Fluent
@JsonInclude(JsonInclude.Include.NON_NULL)
public class TransportDtModel {
    @JsonProperty(value = DigitalTwinsJsonPropertyNames.DIGITAL_TWIN_ID, required = true)
    private String $dtId;
    @JsonProperty(value = "phase", required = true)
    private Phase phase;
    @JsonProperty(value = "route", required = true)
    private TransportRoute route;
    @JsonProperty(value = "startDateTime", required = true)
    private LocalDateTime startDateTime;
    @JsonProperty(value = "endDateTime")
    private LocalDateTime endDateTime;

    /**
     * @return transport id
     */
    public String getId() {
        return $dtId;
    }

    /**
     * @param $dtId of transport
     *
     * @return TransportDtModel
     */
    public TransportDtModel setId(String $dtId) {
        this.$dtId = $dtId;
        return this;
    }

    /**
     * @return transport phase
     */
    public Phase getPhase() {
        return phase;
    }

    /**
     * @param phase of transport
     *
     * @return TransportDtModel
     */
    public TransportDtModel setPhase(Phase phase) {
        this.phase = phase;
        return this;
    }

    /**
     * @return transport route
     */
    public TransportRoute getRoute() {
        return route;
    }

    /**
     * @param route of transport
     *
     * @return TransportDtModel
     */
    public TransportDtModel setRoute(TransportRoute route) {
        this.route = route;
        return this;
    }

    /**
     * @return start date and time
     */
    public LocalDateTime getStartDateTime() {
        return startDateTime;
    }

    /**
     * @param startDateTime of transport
     *
     * @return TransportDtModel
     */
    public TransportDtModel setStartDateTime(LocalDateTime startDateTime) {
        this.startDateTime = startDateTime;
        return this;
    }

    /**
     * @return end date and time
     */
    public LocalDateTime getEndDateTime() {
        return endDateTime;
    }

    /**
     * @param endDateTime of transport
     *
     * @return TransportDtModel
     */
    public TransportDtModel setEndDateTime(LocalDateTime endDateTime) {
        this.endDateTime = endDateTime;
        return this;
    }
}
