/*
 * Copyright (c) 2022. Giada Gibertoni
 */

package dtModel.vehicle.operator;

/**
 * Class for serializing and deserializing a operator digital twin.
 */
public class OperatorDtModel {
    private OperatorPersonalData personalData;
    private OperatorId operatorId;

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
    public OperatorId getOperatorId() {
        return operatorId;
    }

    /**
     * @param operatorId of operator
     *
     * @return OperatorDtModel
     */
    public OperatorDtModel setOperatorId(OperatorId operatorId) {
        this.operatorId = operatorId;
        return this;
    }
}
