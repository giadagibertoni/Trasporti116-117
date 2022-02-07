/*
 * Copyright (c) 2022. Giada Gibertoni
 */

package patientbc.dtmodel;

import com.azure.core.annotation.Fluent;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Represents the patient's health state and autonomy
 */
@Fluent
@JsonInclude(JsonInclude.Include.NON_NULL)
public class PatientCondition {
    @JsonProperty(value = "system")
    private String system;
    @JsonProperty(value = "code")
    private int code;

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

    /**
     * @param system of patient condition
     *
     * @return PatientCondition
     */
    public PatientCondition setSystem(String system) {
        this.system = system;
        return this;
    }

    /**
     * @param code of patient condition
     *
     * @return PatientCondition
     */
    public PatientCondition setCode(int code) {
        this.code = code;
        return this;
    }

}