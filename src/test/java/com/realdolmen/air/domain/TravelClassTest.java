package com.realdolmen.air.domain;

import com.realdolmen.air.util.Asserter;
import org.junit.Before;
import org.junit.Test;

import java.math.BigDecimal;

import static org.junit.Assert.assertNull;

public class TravelClassTest {
    private TravelClass travelClass;

    private Asserter asserter = new Asserter();

    @Before
    public void setUp(){
        this.travelClass = new TravelClass("Business", 5, new BigDecimal("100.0"), null);
        this.travelClass.setMargin(new BigDecimal("1.05"));
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
