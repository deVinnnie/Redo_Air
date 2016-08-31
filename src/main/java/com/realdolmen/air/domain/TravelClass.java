package com.realdolmen.air.domain;

import javax.persistence.Embeddable;
import javax.validation.constraints.Min;
import java.math.BigDecimal;

@Embeddable
public class TravelClass{

    /**
     * Name of the travel class.
     * Example: Business, First Class, Economy
     */
    private String name;

    /**
     * Number of seats still available for purchase.
     */
    @Min(0)
    private Integer remainingSeats;

    /**
     * Base price set by the Airline Company.
     */
    @Min(0)
    private BigDecimal basePrice;

    /**
     * An overridden price set by ReDo Air.
     *
     * If this field is set then the consumer will pay this amount
     * for his ticket instead of the base price * margin set by the Airline.
     */
    @Min(0)
    private BigDecimal retailPrice;

}