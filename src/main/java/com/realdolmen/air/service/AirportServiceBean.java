package com.realdolmen.air.service;

import com.realdolmen.air.domain.Airport;
import com.realdolmen.air.repository.AirportRepository;

import javax.ejb.*;
import java.util.List;

@Stateless
@LocalBean
public class AirportServiceBean implements AirportService{
    @EJB
    AirportRepository airportRepository;

    @Override
    public List<Airport> getAllAirports(){
        return airportRepository.findAllActiveAndNonActive();
    }

    @Override
    public List<Airport> findAirports(String searchTerm){
        return airportRepository.search(searchTerm);
    }

    @Override
    public void toggleAvailability(Long airportId) throws InvalidIdExeption {
        Airport airport = this.findById(airportId);

        if(airport == null){
            throw new InvalidIdExeption("error.airport.doesnotexist");
        }
        airport.setAvailable(!airport.getAvailable());
    }

    @Override
    public Airport findById(Long id){
        return airportRepository.findById(id);
    }
}
