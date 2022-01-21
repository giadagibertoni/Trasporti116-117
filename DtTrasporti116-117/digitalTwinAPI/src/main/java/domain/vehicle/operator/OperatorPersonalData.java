/*
 * Copyright (c) 2022. Giada Gibertoni
 */

package domain.vehicle.operator;


import java.time.LocalDate;

/**
 * Class that represents the operator personal data
 */
public class OperatorPersonalData {
    private final String name;
    private final String surname;
    private final LocalDate birthDate;
    private final OperatorResidence residence;
    private final OperatorTelecom telecom;

    /**
     * Create operator personal data
     *
     * @param n operator name
     * @param s operator surname
     * @param bd operator birth date
     * @param r operator residence
     */
    public OperatorPersonalData(final String n, final String s, final LocalDate bd, final OperatorResidence r, final OperatorTelecom t) {
        this.name = n;
        this.surname = s;
        this.birthDate = bd;
        this.residence = r;
        this.telecom = t;
    }

    /**
     * @return operator name
     */
    public String getName() {
        return name;
    }

    /**
     * @return operator surname
     */
    public String getSurname() {
        return surname;
    }

    /**
     * @return operator birth date
     */
    public LocalDate getBirthDate() {
        return birthDate;
    }

    /**
     * @return operator residence
     */
    public OperatorResidence getResidence() {
        return residence;
    }

    /**
     * @return operator telecom
     */
    public OperatorTelecom getTelecom() {
        return telecom;
    }
}
