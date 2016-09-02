package com.realdolmen.air.service;

import com.realdolmen.air.domain.Airport;
import com.realdolmen.air.repository.AirportRepository;

import javax.ejb.EJB;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import java.util.List;

@Stateless
@LocalBean
public class AirportServiceBean implements AirportService{
    @EJB
    AirportRepository airportRepository;

    @Override
    public List<Airport> getAllAirports(){
        return airportRepository.findAll();
    }

    public List<Airport> findAirports(String searchTerm){
        // Dummy implementation
        return airportRepository.findAll();
    }

    public void toggleAvailability(Long airportId) {
        Airport airport = airportRepository.findById(airportId);
        airport.setAvailable(!airport.getAvailable());
    }

    public Airport findById(Long id){
        return airportRepository.findById(id);

    }
}
