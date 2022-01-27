/*
 * Copyright (c) 2022. Giada Gibertoni
 */

package dtModel.vehicle.operator;

import com.azure.digitaltwins.core.models.DigitalTwinsJsonPropertyNames;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Class for serializing and deserializing a operator digital twin.
 */
public class OperatorDtModel {
    @JsonProperty(value = "personalData")
    private OperatorPersonalData personalData;
    @JsonProperty(value = DigitalTwinsJsonPropertyNames.DIGITAL_TWIN_ID, required = true)
    private String $dtId;

    /**
     * Return the operator's personal data
     *
     * @return operator personal data
     */
    public OperatorPersonalData getPersonalData() {
        return personalData;
    }

    /**
     * @param personalData of operator
     *
     * @return OperatorDtModel
     */
    public OperatorDtModel setPersonalData(OperatorPersonalData personalData) {
        this.personalData = personalData;
        return this;
    }

    /**
     * Return the operator's id
     *
     * @return operator id
     */
    public String getOperatorId() {
        return $dtId;
    }

    /**
     * @param $dtId of operator
     *
     * @return OperatorDtModel
     */
    public OperatorDtModel setOperatorId(String $dtId) {
        this.$dtId = $dtId;
        return this;
    }
}
