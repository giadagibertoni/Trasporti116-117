/*
 * Copyright (c) 2022. Giada Gibertoni
 */

package dtmodel;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Class that represents an address.
 */
public class Address {
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
    public Address setAddress(final String address) {
        this.address = address;
        return this;
    }
}

