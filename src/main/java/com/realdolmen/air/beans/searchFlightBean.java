package com.realdolmen.air.beans;

import com.realdolmen.air.domain.AirlineCompany;
import com.realdolmen.air.domain.Flight;

import javax.annotation.PostConstruct;
import javax.ejb.LocalBean;
import javax.faces.bean.ManagedBean;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

@LocalBean
@ManagedBean
public class searchFlightBean implements Serializable {
    private Flight flight;
    private AirlineCompany airlineCompany;
    private String flightClass;
    private String numberOfSeats;
    private String airlineCompanyName;
    private List<AirlineCompany> airlineCompanies;

    public searchFlightBean(){
    }

    @PostConstruct
    public void postConstruct(){
        airlineCompanies = new ArrayList<>();
        AirlineCompany d = new AirlineCompany();
        d.setName("ll");
        airlineCompanies.add(d);
        flight = new Flight();
        airlineCompany = new AirlineCompany();
    }

    public String getNumberOfSeats() {
        return numberOfSeats;
    }

    public void setNumberOfSeats(String numberOfSeats) {
        this.numberOfSeats = numberOfSeats;
    }

    public Flight getFlight() {
        return flight;
    }

    public void setFlight(Flight flight) {
        this.flight = flight;
    }

    public AirlineCompany getAirlineCompany() {
        return airlineCompany;
    }

    public void setAirlineCompany(AirlineCompany airlineCompany) {
        this.airlineCompany = airlineCompany;
    }

    public String getFlightClass() {
        return flightClass;
    }

    public void setFlightClass(String flightClass) {
        this.flightClass = flightClass;
    }

    public String getAirlineCompanyName() {
        return airlineCompanyName;
    }

    public void setAirlineCompanyName(String airlineCompanyName) {
        this.airlineCompanyName = airlineCompanyName;
    }

    public List<AirlineCompany> getAirlineCompanies() {
        return airlineCompanies;
    }

    public void setAirlineCompanies(List<AirlineCompany> airlineCompanies) {
        this.airlineCompanies = airlineCompanies;
    }
}
