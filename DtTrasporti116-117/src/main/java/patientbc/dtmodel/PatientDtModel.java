/*
 * Copyright (c) 2022. Giada Gibertoni
 */

package patientbc.dtmodel;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Class for serializing and deserializing a patient digital twin.
 */
public class PatientDtModel {
    @JsonProperty(value = "personalData")
    private PatientPersonalData personalData;
    @JsonProperty(value = "condition")
    private PatientCondition condition;

    /**
     * Return the patient's personal data
     *
     * @return patient personal data
     */
    public PatientPersonalData getPersonalData() {
        return personalData;
    }

    /**
     * Return the patient's condition
     *
     * @return patient condition
     */
    public PatientCondition getCondition() {
        return condition;
    }

    /**
     * @param personalData of patient
     *
     * @return PatientDtModel
     */
    public PatientDtModel setPersonalData(PatientPersonalData personalData) {
        this.personalData = personalData;
        return this;
    }

    /**
     * @param condition of patient
     *
     * @return PatientDtModel
     */
    public PatientDtModel setCondition(PatientCondition condition) {
        this.condition = condition;
        return this;
    }

}
