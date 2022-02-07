/*
 * Copyright (c) 2022. Giada Gibertoni
 */

package transportbc.dtmodel;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Class that represents a transport location
 */
public class TransportLocation {
    @JsonProperty(value = "address")
    private TransportAddress address;
    @JsonProperty(value = "city")
    private TransportCity city;
    @JsonProperty(value = "district")
    private TransportDistrict district;
    @JsonProperty(value = "postalCode")
    private TransportPostalCode postalCode;

    /**
     * @return address
     */
    public TransportAddress getAddress() {
        return address;
    }

    /**
     * @return city
     */
    public TransportCity getCity() {
        return city;
    }

    /**
     * @return district
     */
    public TransportDistrict getDistrict() {
        return district;
    }

    /**
     * @return postal code
     */
    public TransportPostalCode getPostalCode() {
        return postalCode;
    }

    /**
     *
     * @param address of transport location
     *
     * @return TransportLocation
     */
    public TransportLocation setAddress(TransportAddress address) {
        this.address = address;
        return this;
    }

    /**
     *
     * @param city of transport location
     *
     * @return TransportLocation
     */
    public TransportLocation setCity(TransportCity city) {
        this.city = city;
        return this;
    }

    /**
     *
     * @param district of transport location
     *
     * @return TransportLocation
     */
    public TransportLocation setDistrict(TransportDistrict district) {
        this.district = district;
        return this;
    }

    /**
     *
     * @param postalCode of transport location
     *
     * @return TransportLocation
     */
    public TransportLocation setPostalCode(TransportPostalCode postalCode) {
        this.postalCode = postalCode;
        return this;
    }
}

