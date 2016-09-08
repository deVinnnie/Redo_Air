package com.realdolmen.air.domain;

import javax.persistence.Entity;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotNull;
import java.util.List;

@Entity
@NamedQueries({
        @NamedQuery(name = AirlineCompany.FIND_ALL, query = "SELECT a FROM AirlineCompany a order by a.name asc "),
        @NamedQuery(name = AirlineCompany.FIND_ALL_SEARCH, query = "SELECT a FROM AirlineCompany a WHERE a.name LIKE :searchString order by a.name asc"),
        @NamedQuery(name = AirlineCompany.FIND_ALL_ACTIVE, query = "SELECT a FROM AirlineCompany  a WHERE a.available = TRUE order by a.name asc")
})
public class AirlineCompany extends AbstractEntity{
    public static final String FIND_ALL = "AirlineCompany.findAll";
    public static final String FIND_ALL_SEARCH = "AirlineCompany.findAllSearch";
    public static final String FIND_ALL_ACTIVE = "AirlineCompany.findAllActive";
    private String name;

    @OneToMany(mappedBy = "airlineCompany")
    private List<Flight> flights;

    @NotNull
    private Boolean available = false;

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

    public Boolean getAvailable() {
        return available;
    }

    public void setAvailable(Boolean available) {
        this.available = available;
    }
}
