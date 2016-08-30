package com.realdolmen.course.domain;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Entity
public class Ticket extends AbstractEntity implements Serializable{

    private Double price;

    @ManyToOne
    private Passenger passenger;

    @ManyToOne
    private Flight outFlight;

    @ManyToOne
    private Flight returnFlight;

    private Status status;



    public Ticket() {
    }

    public Ticket(Double price, Passenger passenger, Flight outFlight, Flight returnFlight) {
        this.price = price;
        this.passenger = passenger;
        this.outFlight = outFlight;
        this.returnFlight = returnFlight;
    }

    public Ticket(Double price){
        this.price = price;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public Passenger getPassenger() {
        return passenger;
    }

    public void setPassenger(Passenger passenger) {
        this.passenger = passenger;
    }

    public Flight getOutFlight() {
        return outFlight;
    }

    public void setOutFlight(Flight outFlight) {
        this.outFlight = outFlight;
    }

    public Flight getReturnFlight() {
        return returnFlight;
    }

    public void setReturnFlight(Flight returnFlight) {
        this.returnFlight = returnFlight;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }
}
