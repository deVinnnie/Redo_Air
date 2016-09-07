package com.realdolmen.air.domain;

import com.realdolmen.air.util.Asserter;
import org.junit.Test;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;

public class BookingTest {

    private Asserter asserter = new Asserter();

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
}
