/*
 * Copyright (c) 2022. Giada Gibertoni
 */

package dtModel.transport;

import dtModel.*;

/**
 * Class that represents a transport location
 */
public class TransportLocation {
    private final Address address;
    private final City city;
    private final District district;
    private final PostalCode postalCode;

    /**
     * Create transport location
     *
     * @param a location address
     * @param c location city
     * @param d location district
     * @param pC location postal code
     */
    public TransportLocation(final Address a, final City c, final District d, final PostalCode pC) {
        this.address = a;
        this.city = c;
        this.district = d;
        this.postalCode = pC;
    }

    /**
     * @return address
     */
    public Address getAddress() {
        return address;
    }

    /**
     * @return city
     */
    public City getCity() {
        return city;
    }

    /**
     * @return district
     */
    public District getDistrict() {
        return district;
    }

    /**
     * @return postal code
     */
    public PostalCode getPostalCode() {
        return postalCode;
    }
}

