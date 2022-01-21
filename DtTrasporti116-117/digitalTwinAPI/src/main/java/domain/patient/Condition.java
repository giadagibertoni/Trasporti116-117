/*
 * Copyright (c) 2022. Giada Gibertoni
 */

package domain.patient;

/**
 * Represents the patient's health state and autonomy
 */
public class Condition {
    private final String description;
    private final String code;

    /**
     * Patient's Condition
     *
     * @param c code of condition
     * @param d description of condition
     */
    public Condition(final String c, final String d) {
        this.code = c;
        this.description = d;
    }

    /**
     * @return description
     */
    public String getDescription() {
        return description;
    }

    /**
     * @return code
     */
    public String getCode() {
        return code;
    }
}