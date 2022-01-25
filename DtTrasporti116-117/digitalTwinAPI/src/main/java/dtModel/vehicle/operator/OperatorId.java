/*
 * Copyright (c) 2022. Giada Gibertoni
 */

package dtModel.vehicle.operator;

/**
 * Class that represents the operator identifier
 */
public class OperatorId {
    private final String id;

    /**
     * Create an operator identifier
     *
     * @param opId operator identifier
     */
    public OperatorId(final String opId) {
        this.id = opId;
    }

    /**
     * @return operator identifier
     */
    public String getId() {
        return this.id;
    }
}