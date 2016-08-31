package com.realdolmen.air.domain;

import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
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

    @Embedded
    private Passenger passenger;

    @ManyToOne
    private Flight flight;

    private String travelClass;

    public Ticket() {
    }

    public Passenger getPassenger() {
        return passenger;
    }

    public void setPassenger(Passenger passenger) {
        this.passenger = passenger;
    }

    public Flight getFlight() {
        return flight;
    }

    public void setFlight(Flight outFlight) {
        this.flight = outFlight;
    }

    public BigDecimal getSoldPrice() {
        return soldPrice;
    }

    public void setSoldPrice(BigDecimal soldPrice) {
        this.soldPrice = soldPrice;
    }

    public BigDecimal getBuyPrice() {
        return buyPrice;
    }

    public void setBuyPrice(BigDecimal buyPrice) {
        this.buyPrice = buyPrice;
    }
}
