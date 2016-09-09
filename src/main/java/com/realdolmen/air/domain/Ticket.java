package com.realdolmen.air.domain;

import javax.persistence.*;
import javax.validation.constraints.Min;
import java.io.Serializable;
import java.math.BigDecimal;

@Entity
public class Ticket extends AbstractEntity implements Serializable{

    /**
     * Price payed by consumer to ReDo Air.
     */
    @Min(0)
    private BigDecimal soldPrice;

    /**
     * Price payed by ReDo Air to the Airline Company.
     */
    @Min(0)
    private BigDecimal buyPrice;

    /**
     * A ticket is bound to a specific passenger. This could be different from the customer.
     * A customer can buy tickets for himself, for others, or both.
     */
    @Embedded
    private Passenger passenger;

    @ManyToOne
    @JoinColumn(name = "travelClass_id")
    private TravelClass travelClass;

    public Ticket() {}

    public Ticket(Passenger passenger, TravelClass travelClass) {
        this.passenger = passenger;
        this.travelClass = travelClass;
    }

    //<editor-fold desc="Getters & Setters">
    public Passenger getPassenger() {
        return passenger;
    }

    public void setPassenger(Passenger passenger) {
        this.passenger = passenger;
    }

    public BigDecimal getSoldPrice() {
        return soldPrice;
    }

    public void setSoldPrice(BigDecimal soldPrice) {
        this.soldPrice = soldPrice;
    }

    public BigDecimal getBuyPrice() {
        return this.travelClass.getEndUserPrice();
    }

    public void setBuyPrice(BigDecimal buyPrice) {
        this.buyPrice = buyPrice;
    }

    public TravelClass getTravelClass() {
        return travelClass;
    }

    public void setTravelClass(TravelClass travelClass) {
        this.travelClass = travelClass;
    }
    //</editor-fold>
}
