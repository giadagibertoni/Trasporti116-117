/*
 * Copyright (c) 2022. Giada Gibertoni
 */

package domain.transport;

public enum Phase {
    /**
     * Transport is scheduled
     */
    SCHEDULED(1),
    /**
     * Transport is in progress
     */
    IN_PROGRESS(2),
    /**
     * Transport is completed
     */
    COMPLETED(3);

    private final int value;

    /**
     * Transport's phase
     *
     * @param v phase
     */
    Phase(final int v) {
        this.value = v;
    }

    /**
     * @return transport's phase
     */
    public int getValue() {
        return this.value;
    }

}
