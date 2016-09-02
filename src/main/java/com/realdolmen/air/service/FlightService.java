package com.realdolmen.air.service;

import com.realdolmen.air.domain.AirlineCompany;
import com.realdolmen.air.domain.Flight;

import javax.ejb.Remote;
import java.util.List;

@Remote
public interface FlightService {
    List<Flight> findAllFlights();
}
