/*
 * Copyright (c) 2022. Giada Gibertoni
 */

package domain;

/**
 * Class that represents an address
 */
public class Address {
    private final String address;

    /**
     * Create address
     *
     * @param a location address
     */
    public Address(final String a) {
        this.address = a;
    }

    /**
     * @return address string
     */
    public String getAddress() {
        return this.address;
    }
}

