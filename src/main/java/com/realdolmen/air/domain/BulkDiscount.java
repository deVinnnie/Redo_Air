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

    @Min(1)
    @Max(100)
    private Integer discountPercentage;
}
