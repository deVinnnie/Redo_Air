package com.realdolmen.air.domain;

import javax.persistence.*;
import java.io.Serializable;
import java.time.*;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Set;

@Entity
@NamedQueries({
        @NamedQuery(name = Flight.FIND_ALL, query = "SELECT f FROM Flight f where " +
                "f.departure.available = TRUE " +
                "AND f.arrival.available = TRUE " +
                "AND f.airlineCompany.available = TRUE"),
        @NamedQuery(name = Flight.FIND_SEARCH, query = "SELECT f from Flight f inner join f.travelClasses t where" +
                " t.name = :flightClass " +
                "AND t.remainingSeats >= :numberOfSeats " +
                "AND f.airlineCompany.id = :airlineCompanyId " +
                "AND f.departure.id = :departureAirportId " +
                "AND f.arrival.id = :arrivalAirportId "),
})
public class Flight extends AbstractEntity{
    public static final String FIND_ALL = "Flight.findAll";
    public static final String FIND_SEARCH = "Flight.findSearch";
    /**
     * Identification number of the flight.
     *
     */
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

    @OneToMany(mappedBy = "flight")
    private List<TravelClass> travelClasses;

    /**
     * A list of discounts associated to this flight.
     */
    @ElementCollection(fetch = FetchType.EAGER)
    @CollectionTable(name = "BulkDiscount")
    @Column(name = "flight")
    private List<BulkDiscount> discounts;

    @OneToMany
    private List<Ticket> tickets;


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

    public List<TravelClass> getTravelClasses() {
        return travelClasses;
    }

    public void setTravelClasses(List<TravelClass> travelClasses) {
        this.travelClasses = travelClasses;
    }

    public List<BulkDiscount> getDiscounts() {
        return discounts;
    }

    public void setDiscounts(List<BulkDiscount> discounts) {
        this.discounts = discounts;
    }
    //</editor-fold>

    public TravelClass findTravelClass(String name){
        for(TravelClass travelClass : this.travelClasses){
            if(travelClass.getName().equals(name)){
                return travelClass;
            }
        }
        return null;
    }

    public Duration getDuration(){
        LocalDateTime departure = LocalDateTime.ofInstant(departureTime.toInstant(), ZoneId.systemDefault()).withNano(0);
        LocalDateTime arrival = LocalDateTime.ofInstant(arrivalTime.toInstant(), ZoneId.systemDefault()).withNano(0);

        return Duration.between(departure, arrival);
    }
}
