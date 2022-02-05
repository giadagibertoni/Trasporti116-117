/*
 * Copyright (c) 2022. Giada Gibertoni
 */

package dtmodel.patient;

import com.fasterxml.jackson.annotation.JsonProperty;
import dtmodel.*;

public class PatientResidence {
    @JsonProperty(value = "address")
    private Address address;
    @JsonProperty(value = "city")
    private City city;
    @JsonProperty(value = "district")
    private District district;
    @JsonProperty(value = "postalCode")
    private PostalCode postalCode;

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

    /**
     * @param address of patient residence
     *
     * @return PatientCondition
     */
    public PatientResidence setAddress(Address address) {
        this.address = address;
        return this;
    }

    /**
     * @param city of patient residence
     *
     * @return PatientCondition
     */
    public PatientResidence setCity(City city) {
        this.city = city;
        return this;
    }

    /**
     * @param district of patient residence
     *
     * @return PatientCondition
     */
    public PatientResidence setDistrict(District district) {
        this.district = district;
        return this;
    }

    /**
     * @param postalCode of patient residence
     *
     * @return PatientCondition
     */
    public PatientResidence setPostalCode(PostalCode postalCode) {
        this.postalCode = postalCode;
        return this;
    }
}
