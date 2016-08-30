package com.realdolmen.course.web.controller;

import com.realdolmen.course.domain.Passenger;
import com.realdolmen.course.repository.PassengerRepository;

import javax.annotation.PostConstruct;
import javax.enterprise.context.RequestScoped;
import javax.faces.event.AjaxBehaviorEvent;
import javax.inject.Inject;
import javax.inject.Named;
import java.util.List;

@Named
@RequestScoped
public class AllPassengerBean {

    private List<Passenger> passengers;

    @Inject
    private PassengerRepository passengerRepository;

    private String searchTerm;



    @PostConstruct
    public void setUp(){
        this.passengers = passengerRepository.findAll();
    }

    public void findPassengers(AjaxBehaviorEvent ajaxBehaviorEvent) {
        this.passengers = passengerRepository.search(this.searchTerm.toLowerCase());
    }

    public List<Passenger> getPassengers() {
        return passengers;
    }

    public void setPassengers(List<Passenger> passengers) {
        this.passengers = passengers;
    }

    public String getSearchTerm() {
        return searchTerm;
    }

    public void setSearchTerm(String searchTerm) {
        this.searchTerm = searchTerm;
    }

    public PassengerRepository getPassengerRepository() {
        return passengerRepository;
    }

    public void setPassengerRepository(PassengerRepository passengerRepository) {
        this.passengerRepository = passengerRepository;
    }
}
