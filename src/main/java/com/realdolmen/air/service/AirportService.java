package com.realdolmen.air.service;

import com.realdolmen.air.domain.Airport;

import javax.ejb.Remote;
import java.util.List;

@Remote
public interface AirportService {
    List<Airport> getAllAirports();
    List<Airport> findAirports(String searchTerm);
    void toggleAvailability(Long airportId) throws InvalidIdExeption;
    Airport findById(Long id);
}
