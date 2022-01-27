/*
 * Copyright (c) 2022. Giada Gibertoni
 */

package dtModel;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Class that represents an address
 */
public class Address {
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
    public Address setAddress(String address) {
        this.address = address;
        return this;
    }
}

