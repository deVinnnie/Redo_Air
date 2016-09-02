package com.realdolmen.air.domain;

import com.realdolmen.air.util.Asserter;
import org.junit.Before;
import org.junit.Test;

import java.math.BigDecimal;

public class TravelClassTest {
    private TravelClass travelClass;

    private Asserter asserter = new Asserter();

    @Before
    public void setUp(){
        this.travelClass = new TravelClass("Business", 5, new BigDecimal("100.0"), null);
        this.travelClass.setMargin(new BigDecimal("1.05"));
    }

    @Test
    public void testCalculateEndUserPriceGivesCorrectResult(){
        BigDecimal expected = new BigDecimal("105.00");
        asserter.assertBigDecimalEqual(expected, travelClass.getEndUserPrice());
    }

    @Test
    public void testCalculateEndUserPriceWithBasePriceZeroGivesResultZero(){
        BigDecimal expected = new BigDecimal("0");
        travelClass.setBasePrice(new BigDecimal("0"));
        asserter.assertBigDecimalEqual(expected, travelClass.getEndUserPrice());
    }

    @Test
    public void testCalculateEndUserPriceWithOverriddenPriceGivesOverriddenValue(){
        BigDecimal overridenPrice = new BigDecimal("200.0");
        this.travelClass.setOverriddenPrice(overridenPrice);

        asserter.assertBigDecimalEqual(overridenPrice, travelClass.getEndUserPrice());
    }
}
