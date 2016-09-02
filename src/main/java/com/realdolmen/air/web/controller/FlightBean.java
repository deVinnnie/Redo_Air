package com.realdolmen.air.web.controller;

import com.realdolmen.air.domain.Flight;
import com.realdolmen.air.domain.TravelClass;
import com.realdolmen.air.repository.FlightRepository;


import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.inject.Inject;
import java.util.List;

@ManagedBean
@ViewScoped
public class FlightBean {
    private Flight flight;

    private List<TravelClass> travelClassSet;

    @Inject
    private FlightRepository repo;

    @PostConstruct
    public void setUp(){
        this.flight = repo.findById(200L);
        this.travelClassSet = repo.findTravelClasses(200L);
        System.out.println("Flight: " + flight.getId());
    }

    public Flight getFlight() {
        return flight;
    }

    public void setFlight(Flight flight) {
        this.flight = flight;
    }

    public List<TravelClass> getTravelClassSet() {
        return travelClassSet;
    }

    public void setTravelClassSet(List<TravelClass> travelClassSet) {
        this.travelClassSet = travelClassSet;
    }

    public void save(){
        System.out.println("Flight: " + flight.getId());
        for(TravelClass t : flight.getTravelClasses()){
            System.out.println(t.getName() + " - " + t.getOverriddenPrice());
        }

        this.repo.update(flight);
    }
}
