package com.realdolmen.air.service;

import com.realdolmen.air.domain.Flight;
import com.realdolmen.air.repository.FlightRepository;

import javax.ejb.EJB;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import java.util.List;

@Stateless
@LocalBean
public class FlightServiceBean implements FlightService{
    @EJB
    FlightRepository flightRepository;

    @Override
    public List<Flight> findAllFlights(){
        return flightRepository.findAll();
    }
}
