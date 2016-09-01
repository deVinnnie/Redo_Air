package com.realdolmen.air.web.controller;

import com.realdolmen.air.domain.TravelClass;
import com.realdolmen.air.repository.TravelClassRepository;


import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

@ManagedBean
@ViewScoped
public class TravelClassBean {

    private Long travelClassId;

    private TravelClass travelClass;

    @Inject
    private TravelClassRepository repository;

    /**
     * Method called after request parameters are loaded in JSF page.
     * Defined in .xhtml file with the f:viewAction tag.
     *
     * The PostConstruct annotation doesn't work because the parameters
     * are not yet copied to the instance variables.
     * This would result in faulty initialisation.
     */
    public void onParametersLoaded(){
        this.travelClass = repository.find(travelClassId);
    }

    /*public String overridePrice(Long travelClassId){
        /*this.travelClass = repository.find(travelClassId);
        return "/redo-admin/override.xhtml";*/
    /*}*/

    public TravelClass getTravelClass() {
        return travelClass;
    }

    public void setTravelClass(TravelClass travelClass) {
        this.travelClass = travelClass;
    }

    public Long getTravelClassId() {
        return travelClassId;
    }

    public void setTravelClassId(Long travelClassId) {
        this.travelClassId = travelClassId;
    }

    public void save(){
        System.out.println(travelClass.getId());
        System.out.println(travelClass.getFlight().getId());
        this.travelClass = this.repository.update(this.travelClass);
    }
}
