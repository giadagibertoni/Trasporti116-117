/*
 * Copyright (c) 2022. Giada Gibertoni
 */

package vehiclebc.dtmodel.operator;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Class that represents a district
 */
public class OperatorDistrict {
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
    public OperatorDistrict setDistrict(String district) {
        this.district = district;
        return this;
    }
}
