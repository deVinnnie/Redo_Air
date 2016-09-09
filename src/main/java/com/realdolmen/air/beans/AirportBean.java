package com.realdolmen.air.beans;

import com.realdolmen.air.domain.Airport;
import com.realdolmen.air.service.AirportServiceBean;
import com.realdolmen.air.service.InvalidIdExeption;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.faces.context.Flash;
import javax.faces.event.AjaxBehaviorEvent;
import javax.inject.Inject;
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
        try {
            service.toggleAvailability(airportId);
            this.allAirports = service.getAllAirports();
        }
        catch(InvalidIdExeption e){
            Flash flash = FacesContext.getCurrentInstance().
                    getExternalContext().getFlash();
            flash.put("error", e.getMessage());
        }
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
