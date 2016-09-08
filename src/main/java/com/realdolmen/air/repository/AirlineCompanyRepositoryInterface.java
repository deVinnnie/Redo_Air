package com.realdolmen.air.repository;

import com.realdolmen.air.domain.AirlineCompany;

import java.util.List;

/**
 * Created by AKCAZ47 on 6/09/2016.
 */
public interface AirlineCompanyRepositoryInterface extends Repository<AirlineCompany, Long>{
    List<AirlineCompany> findAll();
    AirlineCompany findById(Long id);
    List<AirlineCompany> findAirlineCompaniesSearch(String searchTerm);
    List<AirlineCompany> findAllActive();
}
