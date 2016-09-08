package com.realdolmen.air.domain;

import com.realdolmen.air.util.Asserter;
import org.junit.Before;
import org.junit.Test;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import java.awt.print.Book;
import java.math.BigDecimal;
import java.util.Set;

import static org.junit.Assert.assertEquals;

public class BookingTest {

    private Asserter asserter = new Asserter();
    private Validator validator;
    private Booking booking;

    @Before
    public void setUp(){
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        this.validator = factory.getValidator();

        booking = new Booking();
        booking.setCustomer(new Customer());
    }

    @Test
    public void dummyBookingShouldBeValid(){
        Set<ConstraintViolation<Booking>> violations = validator.validate(booking);
        assertEquals(0, violations.size());
    }

    @Test
    public void test_getTotalPrice_GivesCorrectResult(){


        BigDecimal totalPrice = new BigDecimal("100.00");
        BigDecimal expected = new BigDecimal("50.0");
        BigDecimal actual = booking.getTotalPrice(totalPrice,
            new Discount(
                new BigDecimal("0.50")
            )
        );

        asserter.assertBigDecimalEqual(expected, actual);
    }

    @Test
    public void test_getTotalPrice_WithDiscountZero_GivesCorrectResult(){
        BigDecimal totalPrice = new BigDecimal("100.00");
        BigDecimal expected = new BigDecimal("100.0");
        BigDecimal actual = booking.getTotalPrice(totalPrice,
            new Discount(
                new BigDecimal("0.00")
            )
        );

        asserter.assertBigDecimalEqual(expected, actual);
    }

    @Test
    public void test_getTotalPrice_WithDiscountNull_GivesCorrectResult(){
        BigDecimal totalPrice = new BigDecimal("100.00");
        BigDecimal expected = new BigDecimal("100.0");
        BigDecimal actual = booking.getTotalPrice(totalPrice, null);

        asserter.assertBigDecimalEqual(expected, actual);
    }

    /**
     * Make sure that the @Valid annotation does its work.
     */
    @Test
    public void invalidDiscountShouldBeInvalid(){
        booking.setDiscount(
            new Discount(new BigDecimal("-1.00"))
        );

        Set<ConstraintViolation<Booking>> violations = validator.validate(booking);
        assertEquals(1, violations.size());
    }

    /**
     * Make sure that the @Valid annotation does its work.
     */
    @Test
    public void validDiscountShouldBeValid(){
        booking.setDiscount(
            new Discount(new BigDecimal("-1.00"))
        );

        Set<ConstraintViolation<Booking>> violations = validator.validate(booking);
        assertEquals(1, violations.size());
    }
}
