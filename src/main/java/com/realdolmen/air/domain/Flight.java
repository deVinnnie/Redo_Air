package com.realdolmen.air.domain;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.List;

@Entity
public class Flight extends AbstractEntity implements Serializable{

    @Transient
    private static final int DEFAULT_MARGIN = 5;

    private String number;

    @ManyToOne
    private AirlineCompany airlineCompany;

    @Temporal(TemporalType.TIMESTAMP)
    private Date departureTime;

    @Temporal(TemporalType.TIMESTAMP)
    private Date arrivalTime;

    @ManyToOne
    private Airport arrival;

    @ManyToOne
    private Airport departure;

    /*@ElementCollection(fetch = FetchType.LAZY)
    @CollectionTable(name = "TravelClass")
    @Column(name = "flight")
    private List<TravelClass> travelClasses;*/

    @OneToMany
    private List<Ticket> tickets;


    /*@ElementCollection(fetch = FetchType.LAZY)
    @CollectionTable(name = "BulkDiscount")
    @Column(name = "flight")
    private List<BulkDiscount> discounts;*/

    public Flight() {
    }

    public Flight(String number, Date departureTime, Date arrivalTime) {
        this.number = number;
        this.departureTime = departureTime;
        this.arrivalTime = arrivalTime;
    }

    //<editor-fold="Getters & Setters">
    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public Date getDepartureTime() {
        return departureTime;
    }

    public void setDepartureTime(Date departureTime) {
        this.departureTime = departureTime;
    }

    public Date getArrivalTime() {
        return arrivalTime;
    }

    public void setArrivalTime(Date arrivalTime) {
        this.arrivalTime = arrivalTime;
    }

    public Airport getArrival() {
        return arrival;
    }

    public void setArrival(Airport arrival) {
        this.arrival = arrival;
    }

    public Airport getDeparture() {
        return departure;
    }

    public void setDeparture(Airport departure) {
        this.departure = departure;
    }

    public AirlineCompany getAirlineCompany() {
        return airlineCompany;
    }

    public void setAirlineCompany(AirlineCompany airlineCompany) {
        this.airlineCompany = airlineCompany;
    }

    public List<Ticket> getTickets() {
        return tickets;
    }

    public void setTickets(List<Ticket> tickets) {
        this.tickets = tickets;
    }

    /*public List<TravelClass> getTravelClasses() {
        return travelClasses;
    }

    public void setTravelClasses(List<TravelClass> travelClasses) {
        this.travelClasses = travelClasses;
    }*/
    //</editor-fold>
}
