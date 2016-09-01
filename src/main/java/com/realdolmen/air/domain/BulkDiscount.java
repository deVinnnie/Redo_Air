package com.realdolmen.air.domain;

import javax.persistence.Embeddable;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;

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
public class BulkDiscount {
    @Min(1)
    private Integer minimumSeats;

    /**
     * The discount percentage to be applied to the base price.
     *
     * Since a discount of 0% has no effect the minimum is 1%.
     */
    @Min(1)
    @Max(100)
    private Integer discountPercentage;

    public BulkDiscount() {
    }

    public BulkDiscount(Integer minimumSeats, Integer discountPercentage) {
        this.minimumSeats = minimumSeats;
        this.discountPercentage = discountPercentage;
    }

    public Integer getMinimumSeats() {
        return minimumSeats;
    }

    public void setMinimumSeats(Integer minimumSeats) {
        this.minimumSeats = minimumSeats;
    }

    public Integer getDiscountPercentage() {
        return discountPercentage;
    }

    public void setDiscountPercentage(Integer discountPercentage) {
        this.discountPercentage = discountPercentage;
    }
}
