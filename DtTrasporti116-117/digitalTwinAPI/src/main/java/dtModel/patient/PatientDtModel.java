/*
 * Copyright (c) 2022. Giada Gibertoni
 */

package dtModel.patient;

/**
 * Class for deserializing a patient digital twin.
 */
public class PatientDtModel {
    private PatientPersonalData personalData;
    private PatientCondition condition;

    public PatientDtModel(){ super(); }

    /**
     * Patient's Digital Twin model
     *
     * @param c patient condition
     * @param pd patient personal data
     */
    public PatientDtModel(PatientCondition c, PatientPersonalData pd) {
        this.personalData = pd;
        this.condition = c;
    }

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
}
