/*
 * Copyright (c) 2022. Giada Gibertoni
 */

package dtModel.transport;

import com.fasterxml.jackson.annotation.JsonProperty;
import dtModel.*;

/**
 * Class that represents a transport location
 */
public class TransportLocation {
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
     * @param address of transport location
     *
     * @return TransportLocation
     */
    public TransportLocation setAddress(Address address) {
        this.address = address;
        return this;
    }

    /**
     *
     * @param city of transport location
     *
     * @return TransportLocation
     */
    public TransportLocation setCity(City city) {
        this.city = city;
        return this;
    }

    /**
     *
     * @param district of transport location
     *
     * @return TransportLocation
     */
    public TransportLocation setDistrict(District district) {
        this.district = district;
        return this;
    }

    /**
     *
     * @param postalCode of transport location
     *
     * @return TransportLocation
     */
    public TransportLocation setPostalCode(PostalCode postalCode) {
        this.postalCode = postalCode;
        return this;
    }
}

