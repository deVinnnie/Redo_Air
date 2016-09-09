package com.realdolmen.air.beans;

import com.realdolmen.air.domain.AirlineCompany;
import com.realdolmen.air.service.AirlineCompanyServiceBean;
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
public class AirlineBean {

    private List<AirlineCompany> allAirlines;

    private String searchTerm;

    @Inject
    private AirlineCompanyServiceBean airlineCompanyServiceBean;

    @PostConstruct
    public void setUp(){
        allAirlines = airlineCompanyServiceBean.getAllAirlineCompanies();
    }

    public List<AirlineCompany> getAllAirlines() {
        return allAirlines;
    }

    public void setAllAirlines(List<AirlineCompany> allAirlines) {
        this.allAirlines = allAirlines;
    }

    public String getSearchTerm() {
        return searchTerm;
    }

    public void setSearchTerm(String searchTerm) {
        this.searchTerm = searchTerm;
    }

    public String toggleAirlineCompanyAvailability(Long airlineCompanyId){
        try {
            airlineCompanyServiceBean.toggleAvailability(airlineCompanyId);
            this.allAirlines = airlineCompanyServiceBean.getAllAirlineCompanies();
        }
        catch(InvalidIdExeption e){
            Flash flash = FacesContext.getCurrentInstance().
                    getExternalContext().getFlash();
            flash.put("error", e.getMessage());
        }
        return "airlines.xhtml?faces-redirect=true";
    }

    public void searchAirlines(){
        if(searchTerm == null || searchTerm.trim().isEmpty()){
            this.allAirlines = airlineCompanyServiceBean.getAllAirlineCompanies();
        }
        else {
            this.allAirlines = airlineCompanyServiceBean.findAirlineCompaniesSearch(searchTerm);
        }
    }

    public void searchAirports(AjaxBehaviorEvent event){
        this.searchAirlines();
    }
}

