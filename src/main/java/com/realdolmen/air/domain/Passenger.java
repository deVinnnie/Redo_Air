package com.realdolmen.air.domain;

import javax.persistence.Embeddable;

@Embeddable
public class Passenger{
    String firstName;

    String lastName;

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }
}
