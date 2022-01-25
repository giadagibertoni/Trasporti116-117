/*
 * Copyright (c) 2022. Giada Gibertoni
 */

package dtModel;

/**
 * Class that represents a city
 */
public class City {
    private String city;

    public City() {
        super();
    }

    /**
     * Create new DTDLCity
     *
     * @param name city name
     */
    public City(final String name) {
        this.city = name;
    }

    /**
     * @return city name
     */
    public String getCity() {
        return this.city;
    }
}
