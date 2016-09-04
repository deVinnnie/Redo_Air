package com.realdolmen.air.service;

import com.realdolmen.air.domain.Airport;
import com.realdolmen.air.repository.AirportRepository;

import javax.ejb.EJB;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityNotFoundException;
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
        return airportRepository.search(searchTerm);
    }

    public void toggleAvailability(Long airportId) throws InvalidIdExeption {
        Airport airport = this.findById(airportId);

        if(airport == null){
            throw new InvalidIdExeption("error.airport.doesnotexist");
        }
        airport.setAvailable(!airport.getAvailable());
    }

    public Airport findById(Long id){
        return airportRepository.findById(id);
    }
}
