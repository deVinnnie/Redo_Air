package com.realdolmen.air.domain;

import javax.persistence.*;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

@Entity
public class TravelClass extends AbstractEntity{

    @ManyToOne
    @JoinColumn(name="flight_id")
    private Flight flight;

    /**
     * The default profit margin defined as a multiplier.
     *
     * Example: A value of 1.05 corresponds to a profit margin of 5%.
     */
    @Transient
    private BigDecimal margin = new BigDecimal("1.05");

    /**
     * Name of the travel class.
     * Example: Business, First Class, Economy
     */
    @NotNull
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
    private BigDecimal overriddenPrice = null;

    public TravelClass() {
    }

    public TravelClass(String name, Integer remainingSeats, BigDecimal basePrice, BigDecimal overriddenPrice) {
        this.name = name;
        this.remainingSeats = remainingSeats;
        this.basePrice = basePrice;
        this.overriddenPrice = overriddenPrice;
    }

    //<editor-fold desc="Getters & Setters">
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getRemainingSeats() {
        return remainingSeats;
    }

    public void setRemainingSeats(Integer remainingSeats) {
        this.remainingSeats = remainingSeats;
    }

    public BigDecimal getBasePrice() {
        return basePrice;
    }

    public void setBasePrice(BigDecimal basePrice) {
        this.basePrice = basePrice;
    }

    public BigDecimal getOverriddenPrice() {
        return overriddenPrice;
    }

    public void setOverriddenPrice(BigDecimal overriddenPrice) {
        this.overriddenPrice = overriddenPrice;
    }


    public Flight getFlight() {
        return flight;
    }

    public void setFlight(Flight flight) {
        this.flight = flight;
    }
    //</editor-fold>

    // Make package protected. These should only be changed by Test cases.
    public BigDecimal getMargin() {
        return margin;
    }

    void setMargin(BigDecimal margin) {
        this.margin = margin;
    }

    public BigDecimal getEndUserPrice(){
        if(overriddenPrice != null){
            return overriddenPrice;
        }
        else {
            BigDecimal price = basePrice.multiply(margin);
            return price;
        }
    }
}