/*
 * Copyright (c) 2022. Giada Gibertoni
 */

package dtModel.vehicle.operator;


import dtModel.*;

/**
 * Class that represents the operator residence
 */
public class OperatorResidence {

    private final Address address;
    private final City city;
    private final District district;
    private final PostalCode postalCode;

    /**
     * Create operator residence
     * @param a operator residence address
     * @param c operator residence city
     * @param d operator residence district
     * @param pc operator residence postal code
     */
    public OperatorResidence(final Address a, final City c, final District d, final PostalCode pc) {
        this.address = a;
        this.city = c;
        this.district = d;
        this.postalCode = pc;
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

