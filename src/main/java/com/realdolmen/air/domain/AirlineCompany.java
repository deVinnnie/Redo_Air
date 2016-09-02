package com.realdolmen.air.domain;

import javax.persistence.Entity;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import java.util.List;

@Entity
@NamedQueries({
        @NamedQuery(name = AirlineCompany.FIND_ALL, query = "SELECT a FROM AirlineCompany a")
})
public class AirlineCompany extends AbstractEntity{
    public static final String FIND_ALL = "AirlineCompany.findAll";
    private String name;

    @OneToMany(mappedBy = "airlineCompany")
    private List<Flight> flights;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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
