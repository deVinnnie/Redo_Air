package com.realdolmen.air.domain;

import javax.persistence.Embeddable;
import javax.validation.constraints.DecimalMax;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import java.io.Serializable;
import java.math.BigDecimal;

/**
 * A bulk discount is a discount given when buying a lot of seats in one go.
 * One flight can have multiple discounts, each with a different minimum amount of seats to buy.
 * This discount applies to the purchase of tickets by ReDo Air and is defined as percentage.
 *
 * Examples:
 * 2 seats or more : -5%
 * 5 seats or more : -10%
 */
@Embeddable
public class BulkDiscount implements Serializable{
    @Min(1)
    private Integer minimumSeats;

    /**
     * The discount percentage to be applied to the base price.
     *
     * Since a discount of 0% has no effect the minimum is 1%.
     *
     * Price before discount = 100EUR.
     * 50% -> Resulting price = 50EUR.
     * 100% -> Resulting price = 0EUR.
     */
    @DecimalMin("0.01")
    @DecimalMax("1.00")
    private BigDecimal discountPercentage;

    public BulkDiscount() {
    }

    public BulkDiscount(Integer minimumSeats, BigDecimal discountPercentage) {
        this.minimumSeats = minimumSeats;
        this.discountPercentage = discountPercentage;
    }

    public Integer getMinimumSeats() {
        return minimumSeats;
    }

    public void setMinimumSeats(Integer minimumSeats) {
        this.minimumSeats = minimumSeats;
    }

    public BigDecimal getDiscountPercentage() {
        return discountPercentage;
    }

    public void setDiscountPercentage(BigDecimal discountPercentage) {
        this.discountPercentage = discountPercentage;
    }
}
