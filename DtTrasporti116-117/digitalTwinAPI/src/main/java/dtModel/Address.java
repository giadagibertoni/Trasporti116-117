/*
 * Copyright (c) 2022. Giada Gibertoni
 */

package dtModel;

/**
 * Class that represents an address
 */
public class Address {
    private String address;

    public Address() {
        super();
    }

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

