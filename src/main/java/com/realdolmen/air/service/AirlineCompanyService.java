package com.realdolmen.air.service;

import com.realdolmen.air.domain.AirlineCompany;

import javax.ejb.Remote;
import java.util.List;

@Remote
public interface AirlineCompanyService {
    List<AirlineCompany> getAllAirlineCompanies();
    AirlineCompany findById(Long id);
    List<AirlineCompany> findAirlineCompaniesSearch(String searchTerm);
    void toggleAvailability(Long airlineCompanyId) throws InvalidIdExeption;
}
