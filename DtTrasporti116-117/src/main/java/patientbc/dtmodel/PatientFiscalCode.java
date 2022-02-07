/*
 * Copyright (c) 2022. Giada Gibertoni
 */

package patientbc.dtmodel;

import com.azure.core.annotation.Fluent;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Represents the patient's fiscal code
 */
@Fluent
@JsonInclude(JsonInclude.Include.NON_NULL)
public class PatientFiscalCode {
    @JsonProperty(value = "fiscalCode")
    private String fiscalCode;

    /**
     * Return the patient's fiscal code
     *
     * @return patient fiscal code
     */
    public String getFiscalCode() {
        return this.fiscalCode;
    }

    /**
     *
     * @param fiscalCode of patient
     * @return PatientFiscalCode
     */
    public PatientFiscalCode setFiscalCode(String fiscalCode) {
        this.fiscalCode = fiscalCode;
        return this;
    }
}
