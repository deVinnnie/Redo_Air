package com.realdolmen.air.domain;

import org.hibernate.validator.constraints.NotBlank;

import javax.persistence.Embeddable;
import javax.validation.constraints.Size;
import java.io.Serializable;

@Embeddable
public class Passenger implements Serializable{
    @NotBlank
    @Size(min = 1, max = 255)
    private String firstName;

    @NotBlank
    @Size(min = 1, max = 255)
    private String lastName;

    public Passenger() {
    }

    public Passenger(String firstName, String lastName) {
        this.firstName = firstName;
        this.lastName = lastName;
    }

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
