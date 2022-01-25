/*
 * Copyright (c) 2022. Giada Gibertoni
 */

package dtModel.patient;

import dtModel.*;

public class PatientResidence {
    private Address address;
    private City city;
    private District district;
    private PostalCode postalCode;

    public PatientResidence(){
        super();
    }
    /**
     * Patient's residence
     *
     * @param a patient's address
     * @param c patient's city
     * @param d patient's district
     * @param pc patient's postal code
     *
     */
    public PatientResidence(final Address a, final City c, final District d, final PostalCode pc) {
        this.address = a;
        this.city = c;
        this.district = d;
        this.postalCode = pc;
    }

    /**
     * @return patient's address
     */
    public Address getAddress() {
        return address;
    }

    /**
     * @return patient's city
     */
    public City getCity() {
        return city;
    }

    /**
     * @return patient's district
     */
    public District getDistrict() {
        return district;
    }

    /**
     * @return patient's postal code
     */
    public PostalCode getPostalCode() {
        return postalCode;
    }
}
