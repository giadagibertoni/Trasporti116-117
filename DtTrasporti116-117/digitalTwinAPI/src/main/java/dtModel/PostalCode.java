/*
 * Copyright (c) 2022. Giada Gibertoni
 */

package dtModel;

/**
 * Class that represents postal code number
 */
public class PostalCode {
    private int postalCode;

    public PostalCode(){
        super();
    }
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