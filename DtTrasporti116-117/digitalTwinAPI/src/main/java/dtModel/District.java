/*
 * Copyright (c) 2022. Giada Gibertoni
 */

package dtModel;

/**
 * Class that represents a district
 */
public class District {
    private String district;

    public District() {
        super();
    }

    /**
     * Create new district
     *
     * @param d address district
     */
    public District(final String d) {
        this.district = d;
    }

    /**
     * @return district name
     */
    public String getDistrict() {
        return this.district;
    }
}
