/*
 * Copyright (c) 2022. Giada Gibertoni
 */

package dtmodel;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Class that represents a district
 */
public class District {
    @JsonProperty(value = "district")
    private String district;

    /**
     * @return district name
     */
    public String getDistrict() {
        return this.district;
    }

    /**
     * @param district of patient residence
     *
     * @return District
     */
    public District setDistrict(String district) {
        this.district = district;
        return this;
    }
}
