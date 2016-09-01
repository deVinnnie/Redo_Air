package com.realdolmen.air.domain;

import javax.persistence.*;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

@Entity
public class TravelClass extends AbstractEntity{

    @ManyToOne
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
    private BigDecimal retailPrice = null;

    public TravelClass() {
    }

    public TravelClass(String name, Integer remainingSeats, BigDecimal basePrice, BigDecimal retailPrice) {
        this.name = name;
        this.remainingSeats = remainingSeats;
        this.basePrice = basePrice;
        this.retailPrice = retailPrice;
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

    public BigDecimal getRetailPrice() {
        return retailPrice;
    }

    public void setRetailPrice(BigDecimal retailPrice) {
        this.retailPrice = retailPrice;
    }


    public Flight getFlight() {
        return flight;
    }

    public void setFlight(Flight flight) {
        this.flight = flight;
    }
    //</editor-fold>

    // Make package protected. These should only be changed by Test cases.
    BigDecimal getMargin() {
        return margin;
    }

    void setMargin(BigDecimal margin) {
        this.margin = margin;
    }

    /*@Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        TravelClass that = (TravelClass) o;

        return name.equals(that.name);
    }*/

    /*@Override
    public int hashCode() {
        return name.hashCode();
    }*/

    public BigDecimal getEndUserPrice(){
        /*BigDecimal baseEndUserPrice = new BigDecimal("0.000");
        if(retailPrice != null){
            return retailPrice;
        }*/
        BigDecimal price = basePrice.multiply(margin);
        return price;
    }

}