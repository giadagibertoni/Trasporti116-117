/*
 * Copyright (c) 2022. Giada Gibertoni
 */

package dtModel.vehicle.operator;

public class OperatorTelecom {
    private final String email;
    private final String telNumber;

    /**
     * Operator's Telecom
     *
     * @param e operator's email
     * @param t operator's telephone number
     *
     */
    public OperatorTelecom(final String e, final String t) {
        this.email = e;
        this.telNumber = t;
    }

    /**
     * @return operator's email
     */
    public String getEmail() {
        return email;
    }

    /**
     * @return operator's telephone number
     */
    public String getTelNumber() {
        return telNumber;
    }

}
