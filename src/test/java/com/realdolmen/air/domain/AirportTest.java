package com.realdolmen.air.domain;

import org.junit.Before;
import org.junit.Test;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;

import java.util.Set;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;

public class AirportTest {

    private Validator validator;

    private Airport dummyAirport;

    /**
     * Word with more than 200 characters.
     */
    private static String LONG_WORD = "Super-lopado­temacho­selacho­galeo­kranio­leipsano­drim­hypo­trimmato­silphio­parao­melito­katakechy­meno­kichl­epi­kossypho­phatto­perister­alektryon­opte­kephallio­kigklo­peleio­lagoio­siraio­baphe­tragano­pterygon";

    @Before
    public void setUp() {
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        this.validator = factory.getValidator();

        dummyAirport = new Airport("JIA", "Java International Airport");
    }

    @Test
    public void test_DefaultValuesAreSetCorrectly(){
        Airport airport = new Airport("JIA", "Java International Airport");
        assertFalse(airport.getAvailable());
    }

    @Test
    public void test_ValidAirport_GivesNoValidationViolations(){
        Airport airport = new Airport("JIA", "Java International Airport");
        Set<ConstraintViolation<Airport>> violations = validator.validate(airport);
        assertEquals(0, violations.size());
    }

    @Test
    public void test_EmptyAirportName_GivesValidationViolations(){
        Airport airport = new Airport("JIA", "");
        Set<ConstraintViolation<Airport>> violations = validator.validate(airport);
        assertEquals(1, violations.size());
    }

    @Test
    public void test_EmptyAirportCode_GivesValidationViolations(){
        Airport airport = new Airport("", "Java International Airport");
        Set<ConstraintViolation<Airport>> violations = validator.validate(airport);
        assertEquals(1, violations.size());
    }

    @Test
    public void test_AvailableFieldEqualToNull_GivesValidationViolations(){
        Airport airport = new Airport("JIA", "Java International Airport");
        airport.setAvailable(null);
        Set<ConstraintViolation<Airport>> violations = validator.validate(airport);
        assertEquals(1, violations.size());
    }

    @Test
    public void test_ValidCountry_GivesNoValidationViolations(){
        dummyAirport.setCountry("Belgium");
        Set<ConstraintViolation<Airport>> violations = validator.validate(dummyAirport);
        assertEquals(0, violations.size());
    }

    @Test
    public void test_CountryNameLargerThan200_GivesValidationViolations(){
        dummyAirport.setCountry(LONG_WORD);
        Set<ConstraintViolation<Airport>> violations = validator.validate(dummyAirport);
        assertEquals(1, violations.size());
    }
}
