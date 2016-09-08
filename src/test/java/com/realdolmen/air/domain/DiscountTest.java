package com.realdolmen.air.domain;

import org.junit.Before;
import org.junit.Test;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import java.math.BigDecimal;
import java.util.Set;

import static org.junit.Assert.assertEquals;

public class DiscountTest {

    private Validator validator;

    private Discount discount;

    @Before
    public void setUp(){
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        this.validator = factory.getValidator();
        this.discount = new Discount();
    }

    @Test
    public void discountPercentageNormalShouldBeValid(){
        discount.setDiscountPercentage(new BigDecimal("0.52"));

        Set<ConstraintViolation<Discount>> violations = validator.validate(discount);
        assertEquals(0, violations.size());
    }

    @Test
    public void discountPercentageNegativeShouldBeInvalid(){
        discount.setDiscountPercentage(new BigDecimal("-20.00"));

        Set<ConstraintViolation<Discount>> violations = validator.validate(discount);
        assertEquals(1, violations.size());
    }

    @Test
    public void discountPercentageLargerThanOneShouldBeInvalid(){
        discount.setDiscountPercentage(new BigDecimal("1.10"));

        Set<ConstraintViolation<Discount>> violations = validator.validate(discount);
        assertEquals(1, violations.size());
    }

    @Test
    public void discountPercentageWithMoreThan2DigitsShouldBeInvalid(){
        discount.setDiscountPercentage(new BigDecimal("0.131"));

        Set<ConstraintViolation<Discount>> violations = validator.validate(discount);
        assertEquals(1, violations.size());
    }

    @Test
    public void discountPercentageNullShouldBeInvalid(){
        discount.setDiscountPercentage(null);

        Set<ConstraintViolation<Discount>> violations = validator.validate(discount);
        assertEquals(1, violations.size());
    }

    @Test
    public void discountPercentageZeroShouldBeValid(){
        discount.setDiscountPercentage(new BigDecimal("0.00"));

        Set<ConstraintViolation<Discount>> violations = validator.validate(discount);
        assertEquals(0, violations.size());
    }

    @Test
    public void discountPercentageOneShouldBeValid(){
        discount.setDiscountPercentage(new BigDecimal("1.00"));

        Set<ConstraintViolation<Discount>> violations = validator.validate(discount);
        assertEquals(0, violations.size());
    }
}
