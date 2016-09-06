package com.realdolmen.air.domain;

import com.realdolmen.air.util.Asserter;
import org.junit.Test;

import java.math.BigDecimal;

public class BookingTest {

    private Asserter asserter = new Asserter();

    @Test
    public void test_getTotalPrice_GivesCorrectResult(){
        Booking booking = new Booking();
        booking.setDiscount(new Discount(
                new BigDecimal("0.50")
        ));

        BigDecimal totalPrice = new BigDecimal("100.00");
        BigDecimal expected = new BigDecimal("50.0");
        BigDecimal actual = booking.getTotalPrice(totalPrice);

        asserter.assertBigDecimalEqual(expected, actual);
    }

    @Test
    public void test_getTotalPrice_WithDiscountZero_GivesCorrectResult(){
        Booking booking = new Booking();
        booking.setDiscount(new Discount(
                new BigDecimal("0.00")
        ));

        BigDecimal totalPrice = new BigDecimal("100.00");
        BigDecimal expected = new BigDecimal("100.0");
        BigDecimal actual = booking.getTotalPrice(totalPrice);

        asserter.assertBigDecimalEqual(expected, actual);
    }
}
