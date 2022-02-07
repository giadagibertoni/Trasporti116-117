/*
 * Copyright (c) 2022. Giada Gibertoni
 */

package vehiclebc.dtmodel.operator;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Class that represents an address.
 */
public class OperatorAddress {
    /**
     * Address
     */
    @JsonProperty(value = "address")
    private String address;

    /**
     * @return address
     */
    public String getAddress() {
        return this.address;
    }

    /**
     * @param address of patient residence
     *
     * @return Address
     */
    public OperatorAddress setAddress(final String address) {
        this.address = address;
        return this;
    }
}

