package com.realdolmen.air.service;

import com.realdolmen.air.domain.AirlineCompany;

import javax.ejb.Remote;
import java.util.List;

@Remote
public interface AirlineCompanyService {
    List<AirlineCompany> getAllAirlineCompanies();
}
