package com.realdolmen.air.service;

import com.realdolmen.air.domain.AirlineCompany;
import com.realdolmen.air.domain.Airport;

import javax.ejb.Remote;
import java.util.List;

@Remote
public interface AirportService {
    List<Airport> getAllAirports();
}
