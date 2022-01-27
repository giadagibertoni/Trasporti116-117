/*
 * Copyright (c) 2022. Giada Gibertoni
 */

package dtModel;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Class that represents a city
 */
public class City {
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
    public City setCity(String city) {
        this.city = city;
        return this;
    }
}
