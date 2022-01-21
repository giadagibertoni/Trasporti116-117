/*
 * Copyright (c) 2022. Giada Gibertoni
 */

package domain.patient;

public class PatientTelecom {
    private final String email;
    private final String telNumber;

    /**
     * Patient's Telecom
     *
     * @param e patient's email
     * @param t patient's telephone number
     *
     */
    public PatientTelecom(final String e, final String t) {
        this.email = e;
        this.telNumber = t;
    }

    /**
     * @return patient's email
     */
    public String getEmail() {
        return email;
    }

    /**
     * @return patient's telephone number
     */
    public String getTelNumber() {
        return telNumber;
    }

}
