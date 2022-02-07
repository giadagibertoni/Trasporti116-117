/*
 * Copyright (c) 2022. Giada Gibertoni
 */

package patientbc.dtmodel;
import com.fasterxml.jackson.annotation.JsonProperty;

public class PatientResidence {
    @JsonProperty(value = "address")
    private PatientAddress address;
    @JsonProperty(value = "city")
    private PatientCity city;
    @JsonProperty(value = "district")
    private PatientDistrict district;
    @JsonProperty(value = "postalCode")
    private PatientPostalCode postalCode;

    /**
     * @return patient's address
     */
    public PatientAddress getAddress() {
        return address;
    }

    /**
     * @return patient's city
     */
    public PatientCity getCity() {
        return city;
    }

    /**
     * @return patient's district
     */
    public PatientDistrict getDistrict() {
        return district;
    }

    /**
     * @return patient's postal code
     */
    public PatientPostalCode getPostalCode() {
        return postalCode;
    }

    /**
     * @param address of patient residence
     *
     * @return PatientCondition
     */
    public PatientResidence setAddress(PatientAddress address) {
        this.address = address;
        return this;
    }

    /**
     * @param city of patient residence
     *
     * @return PatientCondition
     */
    public PatientResidence setCity(PatientCity city) {
        this.city = city;
        return this;
    }

    /**
     * @param district of patient residence
     *
     * @return PatientCondition
     */
    public PatientResidence setDistrict(PatientDistrict district) {
        this.district = district;
        return this;
    }

    /**
     * @param postalCode of patient residence
     *
     * @return PatientCondition
     */
    public PatientResidence setPostalCode(PatientPostalCode postalCode) {
        this.postalCode = postalCode;
        return this;
    }
}
