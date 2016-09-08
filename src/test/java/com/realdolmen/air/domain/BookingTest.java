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
import java.util.Arrays;
import java.util.List;
import java.util.Set;

import static org.junit.Assert.assertEquals;

public class BookingTest {

    private Asserter asserter = new Asserter();
    private Validator validator;

    @Before
    public void setUp(){
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        this.validator = factory.getValidator();
    }

    @Test
    public void test_getTotalPrice_GivesCorrectResult(){
        Booking booking = new Booking();

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
        Booking booking = new Booking();

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
        Booking booking = new Booking();

        BigDecimal totalPrice = new BigDecimal("100.00");
        BigDecimal expected = new BigDecimal("100.0");
        BigDecimal actual = booking.getTotalPrice(totalPrice, null);

        asserter.assertBigDecimalEqual(expected, actual);
    }

    /*@Test
    public void invalidDiscountShouldBeInvalid(){
        Booking booking = new Booking();

        booking.setDiscount(
                new Discount(
                        new BigDecimal("-1.00")
                )
        );

        Set<ConstraintViolation<Booking>> violations = validator.validate(booking);
        assertEquals(0, violations.size());


    }*/

}
