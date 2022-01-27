/*
 * Copyright (c) 2022. Giada Gibertoni
 */

package dtModel.patient;

import com.azure.core.annotation.Fluent;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.time.LocalDate;

/**
 * Represents the patient's personal data
 */
@Fluent
@JsonInclude(JsonInclude.Include.NON_NULL)
public class PatientPersonalData {
    @JsonProperty(value = "fiscalCode")
    private PatientFiscalCode fiscalCode;
    @JsonProperty(value = "name")
    private String name;
    @JsonProperty(value = "surname")
    private String surname;
    @JsonProperty(value = "birthDate")
    private LocalDate birthDate;
    @JsonProperty(value = "residence")
    private PatientResidence residence;


    /**
     * @return name of patient
     */
    public String getName() {
        return name;
    }

    /**
     * @return surname of patient
     */
    public String getSurname() {
        return surname;
    }

    /**
     * @return birth date of patient
     */
    public LocalDate getBirthDate() {
        return birthDate;
    }

    /**
     * @return residence of patient
     */
    public PatientResidence getResidence() {
        return residence;
    }

    /**
     * @return fiscalCode of patient
     */
    public PatientFiscalCode getFiscalCode() {
        return fiscalCode;
    }

    /**
     * @param fiscalCode of patient
     *
     * @return PatientPersonalData
     */
    public PatientPersonalData setFiscalCode(PatientFiscalCode fiscalCode) {
        this.fiscalCode = fiscalCode;
        return this;
    }

    /**
     * @param name of patient
     *
     * @return PatientPersonalData
     */
    public PatientPersonalData setName(String name) {
        this.name = name;
        return this;
    }

    /**
     * @param surname of patient
     *
     * @return PatientPersonalData
     */
    public PatientPersonalData setSurname(String surname) {
        this.surname = surname;
        return this;
    }

    /**
     * @param birthDate of patient
     *
     * @return PatientPersonalData
     */
    public PatientPersonalData setBirthDate(LocalDate birthDate) {
        this.birthDate = birthDate;
        return this;
    }

    /**
     * @param residence of patient
     *
     * @return PatientPersonalData
     */
    public PatientPersonalData setResidence(PatientResidence residence) {
        this.residence = residence;
        return this;
    }
}
