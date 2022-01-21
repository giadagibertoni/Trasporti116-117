/*
 * Copyright (c) 2022. Giada Gibertoni
 */

package domain;

/**
 * Class that represents the house number
 */
public class HouseNumber {
    private final int houseNumber;

    /**
     * Create a new house number
     *
     * @param hN address house number
     */
    public HouseNumber(final int hN) {
        this.houseNumber = hN;
    }

    /**
     * @return house number
     */
    public int getHouseNumber() {
        return this.houseNumber;
    }
}