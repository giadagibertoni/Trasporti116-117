/*
 * Copyright (c) 2022. Giada Gibertoni
 */

package patientbc.dtmodel;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Class that represents a city
 */
public class PatientCity {
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
    public PatientCity setCity(String city) {
        this.city = city;
        return this;
    }
}
