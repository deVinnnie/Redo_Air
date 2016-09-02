package com.realdolmen.air.web.controller;

import com.realdolmen.air.domain.Airport;
import com.realdolmen.air.service.AirportServiceBean;


import javax.annotation.PostConstruct;
import javax.enterprise.context.RequestScoped;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.event.AjaxBehaviorEvent;
import javax.inject.Inject;
import javax.inject.Named;
import java.util.List;

@ManagedBean
@ViewScoped
public class AirportBean {

    private List<Airport> allAirports;

    private String searchTerm;

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

    public String getSearchTerm() {
        return searchTerm;
    }

    public void setSearchTerm(String searchTerm) {
        this.searchTerm = searchTerm;
    }

    public String toggleAirportAvailability(Long airportId){
        service.toggleAvailability(airportId);
        return "airports.xhtml?faces-redirect=true";
    }

    public void searchAirports(){
        if(searchTerm == null || searchTerm.trim().isEmpty()){
            this.allAirports = service.getAllAirports();
        }
        else {
            this.allAirports = service.findAirports(searchTerm);
        }
    }

    public void searchAirports(AjaxBehaviorEvent event){
        this.searchAirports();
    }
}
