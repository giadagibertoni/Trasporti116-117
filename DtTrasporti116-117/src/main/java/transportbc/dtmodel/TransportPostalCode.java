/*
 * Copyright (c) 2022. Giada Gibertoni
 */

package transportbc.dtmodel;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Class that represents postal code number
 */
public class TransportPostalCode {
    @JsonProperty(value = "postalCode")
    private int postalCode;

    /**
     * @return postal code number
     */
    public int getPostalCode() {
        return this.postalCode;
    }

    /**
     * @param postalCode of patient residence
     *
     * @return PostalCode
     */
    public TransportPostalCode setPostalCode(int postalCode) {
        this.postalCode = postalCode;
        return this;
    }
}