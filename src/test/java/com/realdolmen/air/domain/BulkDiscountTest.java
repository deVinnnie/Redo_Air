package com.realdolmen.air.domain;

import org.junit.Before;
import org.junit.Test;

import javax.validation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import static org.junit.Assert.assertEquals;

public class BulkDiscountTest {

    private Validator validator;

    @Before
    public void setUp() {
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        this.validator = factory.getValidator();
    }

    // Test cases:
    // [v] Seats = 0
    // [v] Seats < 0

    // [v] Discount < 0
    // [] Discount > 100
    // [v] Discount = 100
    // [] Discount = 0
    // [v] Discount = Normal

    @Test
    public void testCorrectDiscountValidates(){
        BulkDiscount bulkDiscount;
        Set<ConstraintViolation<BulkDiscount>> violations;

        bulkDiscount = new BulkDiscount(5, 10);

        violations = validator.validate(bulkDiscount);
        assertEquals(0, violations.size());

        bulkDiscount = new BulkDiscount(5, 55);

        violations = validator.validate(bulkDiscount);
        assertEquals(0, violations.size());

        bulkDiscount = new BulkDiscount(5, 95);

        violations = validator.validate(bulkDiscount);
        assertEquals(0, violations.size());
    }

    @Test
    public void testNegativeDiscountReturnsConstraintViolation() {
        BulkDiscount bulkDiscount = new BulkDiscount(5, -1);

        Set<ConstraintViolation<BulkDiscount>> violations = validator.validate(bulkDiscount);
        assertEquals(1, violations.size());
    }

    @Test
    public void testDiscountEqualTo100IsValid(){
        BulkDiscount bulkDiscount = new BulkDiscount(1, 100);

        Set<ConstraintViolation<BulkDiscount>> violations = validator.validate(bulkDiscount);
        assertEquals(0, violations.size());
    }

    @Test
    public void testMinimumSeatsEqualToZeroReturnsConstraintViolation(){
        BulkDiscount bulkDiscount = new BulkDiscount(0, 1);

        Set<ConstraintViolation<BulkDiscount>> violations = validator.validate(bulkDiscount);
        assertEquals(1, violations.size());
    }

    @Test
    public void testMinimumSeatsLessToZeroReturnsConstraintViolation(){
        BulkDiscount bulkDiscount = new BulkDiscount(-1, 1);

        Set<ConstraintViolation<BulkDiscount>> violations = validator.validate(bulkDiscount);
        assertEquals(1, violations.size());
    }






}
