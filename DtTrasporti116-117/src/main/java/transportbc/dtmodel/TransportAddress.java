/*
 * Copyright (c) 2022. Giada Gibertoni
 */

package transportbc.dtmodel;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Class that represents an address.
 */
public class TransportAddress {
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
    public TransportAddress setAddress(final String address) {
        this.address = address;
        return this;
    }
}

