package com.realdolmen.air.domain;

import org.junit.Before;
import org.junit.Test;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;

import java.util.Set;

import static org.junit.Assert.assertEquals;

public class BulkDiscountTest {

    private Validator validator;

    @Before
    public void setUp() {
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        this.validator = factory.getValidator();
    }

    @Test
    public void testCorrectDiscountValidates(){
        BulkDiscount bulkDiscount = new BulkDiscount(5, 10);

        Set<ConstraintViolation<BulkDiscount>> violations = validator.validate(bulkDiscount);
        assertEquals(0, violations.size());
    }

    @Test
    public void testNegativeDiscountReturnsConstraintViolation() {
        BulkDiscount bulkDiscount = new BulkDiscount(5, -1);

        Set<ConstraintViolation<BulkDiscount>> violations = validator.validate(bulkDiscount);
        assertEquals(1, violations.size());
    }

    // Seats = 0
    // Seats < 0

    // Discount < 0
    // Discount > 100
    // Discount = 100
    // Discount = 0
    // Discount = Normal
}
