package com.realdolmen.air.web.controller;

import com.realdolmen.air.domain.TravelClass;
import com.realdolmen.air.repository.TravelClassRepository;
import com.realdolmen.air.service.TravelClassServiceBean;


import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.inject.Inject;

@ManagedBean
@ViewScoped
public class TravelClassBean {

    private Long travelClassId;

    private TravelClass travelClass;

    @Inject
    TravelClassServiceBean travelClassServiceBean;

    //has to go
//    @Inject
//    private TravelClassRepository repository;

    /**
     * Method called after request parameters are loaded in JSF page.
     * Defined in .xhtml file with the f:viewAction tag.
     *
     * The PostConstruct annotation doesn't work because the parameters
     * are not yet copied to the instance variables.
     * This would result in faulty initialisation.
     */
    public void onParametersLoaded(){
        //has to go
//        this.travelClass = repository.find(travelClassId);
        this.travelClass = travelClassServiceBean.find(travelClassId);
    }

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
        //has to go
//        this.travelClass = this.repository.update(this.travelClass);
        this.travelClass = this.travelClassServiceBean.update(this.travelClass);
    }
}
