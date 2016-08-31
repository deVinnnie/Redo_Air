package com.realdolmen.air.domain;

import javax.persistence.Entity;
import javax.persistence.OneToMany;
import java.util.List;

@Entity
public class AirlineCompany extends AbstractEntity{

    private String naam;

    @OneToMany(mappedBy = "airlineCompany")
    private List<Flight> flights;

    public String getNaam() {
        return naam;
    }

    public void setNaam(String naam) {
        this.naam = naam;
    }

    public List<Flight> getFlights() {
        return flights;
    }

    public void setFlights(List<Flight> flights) {
        this.flights = flights;
    }

    public void addFlight(Flight flight){
        flights.add(flight);
    }
}
