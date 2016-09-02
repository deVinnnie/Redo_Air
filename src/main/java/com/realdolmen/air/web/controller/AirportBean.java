package com.realdolmen.air.web.controller;

import com.realdolmen.air.domain.Airport;
import com.realdolmen.air.service.AirportService;
import com.realdolmen.air.service.AirportServiceBean;

import javax.annotation.PostConstruct;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;
import java.util.List;

@Named
@RequestScoped
public class AirportBean {

    private List<Airport> allAirports;

    @Inject
    private AirportServiceBean service;

    @PostConstruct
    public void setUp(){
        allAirports = service.getAllAirports();
    }

    public List<Airport> getAllAirports() {
        return allAirports;
    }

    public void setAllAirports(List<Airport> allAirports) {
        this.allAirports = allAirports;
    }

    public String toggleAirportAvailability(Long airportId){
        service.toggleAvailability(airportId);
        return "airports.xhtml?faces-redirect=true";
    }
}
