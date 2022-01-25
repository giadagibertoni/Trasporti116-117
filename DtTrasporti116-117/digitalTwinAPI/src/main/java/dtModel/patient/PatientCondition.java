/*
 * Copyright (c) 2022. Giada Gibertoni
 */

package dtModel.patient;

/**
 * Represents the patient's health state and autonomy
 */
public class PatientCondition {
    private String system;
    private int code;

    public PatientCondition(){
        super();
    }
    /**
     * Patient's PatientCondition
     *
     * @param c code of condition
     * @param d description of condition
     */
    public PatientCondition(final int c, final String d) {
        this.code = c;
        this.system = d;
    }

    /**
     * @return description
     */
    public String getSystem() {
        return system;
    }

    /**
     * @return code
     */
    public int getCode() {
        return code;
    }
}