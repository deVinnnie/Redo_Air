package com.realdolmen.air.util;

import org.junit.Assert;

import java.math.BigDecimal;

public class Asserter {

    /**
     * Convenience method to compare BigDecimals in Unit Tests.
     *
     * The equals method of BigDecimal is buggy, because it will return false
     * when the scale/precision of the two numbers is different,
     * even though they may be the exact same number.
     *
     * The compareTo method is used instead. This returns 0 when two numbers are the same.
     *
     * @param bd1 Number 1, Expected value
     * @param bd2 Number 2, Actual Value
     */
    public void assertBigDecimalEqual(BigDecimal bd1, BigDecimal bd2){
        if(bd1.compareTo(bd2) != 0) {
            throw new AssertionError("Expected " + bd1 + " but was " + bd2);
        }
    }
}
