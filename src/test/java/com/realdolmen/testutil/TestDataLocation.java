package com.realdolmen.testutil;

/**
 * Created by knockaert on 5/04/2016.
 */
public enum TestDataLocation {

    AIRLINECOMPANY("data/airlineCompany.xml"),
    AIRPORT("data/airport.xml"),
    CUSTOMER("data/customer.xml"),
    FLIGHT("data/flight.xml"),
    TRAVELCLASS("data/travelClass.xml"),
    BOOKING("data/booking.xml");

    private String value;

    TestDataLocation(String value) {
        this.value = value;
    }

    public String getValue() {
        return this.value;
    }
}
