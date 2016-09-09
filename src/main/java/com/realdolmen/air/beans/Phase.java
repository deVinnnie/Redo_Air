package com.realdolmen.air.beans;

/**
 * Booking phase, used by the wizard.
 */
public enum Phase{
    PASSENGER_INFORMATION(0),
    PAYMENT_METHOD(1),
    PAYMENT_INFORMATION(2),
    CONFIRMATION(3);

    private final int ordinal;

    Phase(int ordinal){
        this.ordinal = ordinal;
    }

    public int getOrdinal() {
        return ordinal;
    }
}