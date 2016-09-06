package com.realdolmen.air.repository;

import com.realdolmen.air.domain.Flight;
import com.realdolmen.air.domain.TravelClass;

import java.util.List;

public interface FlightRepositoryInterface extends Repository<Flight,Long>{
    List<TravelClass> findTravelClasses(Long flightId);
    Flight findById(Long id);
    Flight update(Flight flight);
    List<Flight> findAll();
    List<Flight> findFlightsWithParams(Long airlineCompanyId, String flightClass, int numberOfSeats, Long departureAirportId, Long arrivalAirportId);
}
