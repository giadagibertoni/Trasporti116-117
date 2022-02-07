/*
 * Copyright (c) 2022. Giada Gibertoni
 */

package vehiclebc.dtmodel.operator;


import com.azure.core.annotation.Fluent;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Class that represents the operator residence
 */
@Fluent
@JsonInclude(JsonInclude.Include.NON_NULL)
public class OperatorResidence {
    @JsonProperty(value = "address")
    private OperatorAddress address;
    @JsonProperty(value = "city")
    private OperatorCity city;
    @JsonProperty(value = "district")
    private OperatorDistrict district;
    @JsonProperty(value = "postalCode")
    private OperatorPostalCode postalCode;

    /**
     * @return address
     */
    public OperatorAddress getAddress() {
        return address;
    }

    /**
     * @return city
     */
    public OperatorCity getCity() {
        return city;
    }

    /**
     * @return district
     */
    public OperatorDistrict getDistrict() {
        return district;
    }

    /**
     * @return postal code
     */
    public OperatorPostalCode getPostalCode() {
        return postalCode;
    }

    /**
     *
     * @param address of operator
     *
     * @return OperatorResidence
     */
    public OperatorResidence setAddress(OperatorAddress address) {
        this.address = address;
        return this;
    }

    /**
     *
     * @param city of operator
     *
     * @return OperatorResidence
     */
    public OperatorResidence setCity(OperatorCity city) {
        this.city = city;
        return this;
    }

    /**
     *
     * @param district of operator
     *
     * @return OperatorResidence
     */
    public OperatorResidence setDistrict(OperatorDistrict district) {
        this.district = district;
        return this;
    }

    /**
     *
     * @param postalCode of operator
     *
     * @return OperatorResidence
     */
    public OperatorResidence setPostalCode(OperatorPostalCode postalCode) {
        this.postalCode = postalCode;
        return this;
    }


}

