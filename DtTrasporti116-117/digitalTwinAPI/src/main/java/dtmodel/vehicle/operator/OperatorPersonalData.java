/*
 * Copyright (c) 2022. Giada Gibertoni
 */

package dtmodel.vehicle.operator;


import com.azure.core.annotation.Fluent;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.time.LocalDate;

/**
 * Class that represents the operator personal data
 */
@Fluent
@JsonInclude(JsonInclude.Include.NON_NULL)
public class OperatorPersonalData {
    @JsonProperty(value = "name")
    private String name;
    @JsonProperty(value = "surname")
    private String surname;
    @JsonProperty(value = "birthDate")
    private LocalDate birthDate;
    @JsonProperty(value = "residence")
    private OperatorResidence residence;
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
     *
     * @param name of operator
     *
     * @return OperatorPersonalData
     */
    public OperatorPersonalData setName(String name) {
        this.name = name;
        return this;
    }

    /**
     *
     * @param surname of operator
     *
     * @return OperatorPersonalData
     */
    public OperatorPersonalData setSurname(String surname) {
        this.surname = surname;
        return this;
    }

    /**
     *
     * @param birthDate of operator
     *
     * @return OperatorPersonalData
     */
    public OperatorPersonalData setBirthDate(LocalDate birthDate) {
        this.birthDate = birthDate;
        return this;
    }

    /**
     *
     * @param residence of operator
     *
     * @return OperatorPersonalData
     */
    public OperatorPersonalData setResidence(OperatorResidence residence) {
        this.residence = residence;
        return this;
    }
}
