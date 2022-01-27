/*
 * Copyright (c) 2022. Giada Gibertoni
 */

package dtModel.vehicle.operator;


import com.azure.core.annotation.Fluent;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import dtModel.*;

/**
 * Class that represents the operator residence
 */
@Fluent
@JsonInclude(JsonInclude.Include.NON_NULL)
public class OperatorResidence {
    @JsonProperty(value = "address")
    private Address address;
    @JsonProperty(value = "city")
    private City city;
    @JsonProperty(value = "district")
    private District district;
    @JsonProperty(value = "postalCode")
    private PostalCode postalCode;

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

    /**
     *
     * @param address of operator
     *
     * @return OperatorResidence
     */
    public OperatorResidence setAddress(Address address) {
        this.address = address;
        return this;
    }

    /**
     *
     * @param city of operator
     *
     * @return OperatorResidence
     */
    public OperatorResidence setCity(City city) {
        this.city = city;
        return this;
    }

    /**
     *
     * @param district of operator
     *
     * @return OperatorResidence
     */
    public OperatorResidence setDistrict(District district) {
        this.district = district;
        return this;
    }

    /**
     *
     * @param postalCode of operator
     *
     * @return OperatorResidence
     */
    public OperatorResidence setPostalCode(PostalCode postalCode) {
        this.postalCode = postalCode;
        return this;
    }


}

