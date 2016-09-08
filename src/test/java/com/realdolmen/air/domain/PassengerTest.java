package com.realdolmen.air.domain;

import org.junit.Before;
import org.junit.Test;

import javax.validation.ConstraintViolation;
import java.util.Set;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class PassengerTest extends BeanValidatorTest{

    private Passenger passenger;

    @Before
    public void setUp(){
        super.setUp();
        passenger = new Passenger();
    }

    //<editor-fold desc="First Name">
    @Test
    public void normalFirstNameShouldValidate(){
        passenger.setFirstName("Malcolm");
        Set<ConstraintViolation<Passenger>> violations = getValidator().validateProperty(passenger, "firstName");
        assertTrue(violations.isEmpty());
    }

    @Test
    public void emptyFirstNameShouldNotValidate(){
        passenger.setFirstName("");
        Set<ConstraintViolation<Passenger>> violations = getValidator().validateProperty(passenger, "firstName");
        assertFalse(violations.isEmpty());
    }

    @Test
    public void firstNameNullShouldNotValidate(){
        passenger.setFirstName(null);
        Set<ConstraintViolation<Passenger>> violations = getValidator().validateProperty(passenger, "firstName");
        assertFalse(violations.isEmpty());
    }
    //</editor-fold>

    //<editor-fold desc="Last Name">
    @Test
    public void normalLastNameShouldValidate(){
        passenger.setLastName("Reynolds");
        Set<ConstraintViolation<Passenger>> violations = getValidator().validateProperty(passenger, "lastName");
        assertTrue(violations.isEmpty());
    }

    @Test
    public void emptyLastNameShouldNotValidate(){
        passenger.setLastName("");
        Set<ConstraintViolation<Passenger>> violations = getValidator().validateProperty(passenger, "lastName");
        assertFalse(violations.isEmpty());
    }

    @Test
    public void lastNameNullShouldNotValidate(){
        passenger.setLastName(null);
        Set<ConstraintViolation<Passenger>> violations = getValidator().validateProperty(passenger, "lastName");
        assertFalse(violations.isEmpty());
    }
    //</editor-fold>
}
