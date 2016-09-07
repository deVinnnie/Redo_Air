package com.realdolmen.air.domain;

import com.realdolmen.air.util.Asserter;
import org.junit.Before;
import org.junit.Test;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import java.math.BigDecimal;
import java.util.Set;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

public class TravelClassTest {
    private TravelClass travelClass;

    private Asserter asserter = new Asserter();
    private Validator validator;

    @Before
    public void setUp(){
        this.travelClass = new TravelClass("Business", 5, new BigDecimal("100.0"), null);
        this.travelClass.setMargin(new BigDecimal("1.05"));

        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        this.validator = factory.getValidator();
    }

    @Test
    public void basePriceWithMoreThanTwoDecimalPlacesShouldBeInvalid(){
        travelClass.setBasePrice(
                new BigDecimal("3.141592")
        );
        

        Set<ConstraintViolation<TravelClass>> violations = validator.validate(travelClass);
        assertEquals(1, violations.size());
    }

    @Test
    public void basePriceWitTwoDecimalPlacesShouldBeValid(){
        travelClass.setBasePrice(
                new BigDecimal("3.14")
        );

        Set<ConstraintViolation<TravelClass>> violations = validator.validate(travelClass);
        assertEquals(0, violations.size());
    }

    @Test
    public void overriddenPriceWithMoreThanTwoDecimalPlacesShouldBeInvalid(){
        travelClass.setBasePrice(
                new BigDecimal("3.141592")
        );


        Set<ConstraintViolation<TravelClass>> violations = validator.validate(travelClass);
        assertEquals(1, violations.size());
    }

    @Test
    public void overriddenPriceWitTwoDecimalPlacesShouldBeValid(){
        travelClass.setOverriddenPrice(
                new BigDecimal("3.14")
        );

        Set<ConstraintViolation<TravelClass>> violations = validator.validate(travelClass);
        assertEquals(0, violations.size());
    }


    @Test
    public void test_getEndUserPrice_WithNormalBasePrice_GivesCorrectResult(){
        BigDecimal expected = new BigDecimal("105.00");
        asserter.assertBigDecimalEqual(expected, travelClass.getEndUserPrice());
    }

    @Test
    public void test_getEndUserPrice_WithBasePriceZero_GivesResultZero(){
        BigDecimal expected = new BigDecimal("0");
        travelClass.setBasePrice(new BigDecimal("0"));
        asserter.assertBigDecimalEqual(expected, travelClass.getEndUserPrice());
    }

    @Test
    public void test_getEndUserPrice_WithBasePriceNull_GivesNull(){
        travelClass.setBasePrice(null);
        assertNull(travelClass.getEndUserPrice());
    }

    @Test
    public void test_getEndUserPrice_WithOverriddenPrice_GivesOverriddenValue(){
        BigDecimal overriddenPrice = new BigDecimal("200.0");
        travelClass.setOverriddenPrice(overriddenPrice);

        asserter.assertBigDecimalEqual(overriddenPrice, travelClass.getEndUserPrice());
    }

    @Test
    public void test_getEndUserPrice_WithOverriddenPriceAndBasePriceNull_GivesOverriddenValue(){
        BigDecimal overriddenPrice = new BigDecimal("200.0");
        travelClass.setOverriddenPrice(overriddenPrice);
        travelClass.setBasePrice(null);

        asserter.assertBigDecimalEqual(overriddenPrice, travelClass.getEndUserPrice());
    }
}
