package com.realdolmen.air.repository;

import com.realdolmen.air.domain.Airport;

import java.util.List;

/**
 * Created by AKCAZ47 on 6/09/2016.
 */
public interface AirportRepositoryInterface extends Repository<Airport, Long>{
    List<Airport> findAll();
    List<Airport> findAllActiveAndNonActive();
    Airport findById(Long id);
    List<Airport> search(String searchTerm);
}
