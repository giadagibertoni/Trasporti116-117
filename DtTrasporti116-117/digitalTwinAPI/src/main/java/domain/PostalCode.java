/*
 * Copyright (c) 2022. Giada Gibertoni
 */

package domain;

/**
 * Class that represents postal code number
 */
public class PostalCode {
    private final int postalCode;

    /**
     * Create a new postal code number
     *
     * @param pC postal code number
     */
    public PostalCode(final int pC) {
        this.postalCode = pC;
    }

    /**
     * @return postal code number
     */
    public int getPostalCode() {
        return this.postalCode;
    }
}