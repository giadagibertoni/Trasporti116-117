/*
 * Copyright (c) 2022. Giada Gibertoni
 */

package vehiclebc.dtmodel.operator;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Class that represents a city
 */
public class OperatorCity {
    @JsonProperty(value = "city")
    private String city;

    /**
     * @return city name
     */
    public String getCity() {
        return this.city;
    }

    /**
     * @param city of patient residence
     *
     * @return City
     */
    public OperatorCity setCity(String city) {
        this.city = city;
        return this;
    }
}
