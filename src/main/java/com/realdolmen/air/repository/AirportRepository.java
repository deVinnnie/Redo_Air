package com.realdolmen.air.repository;

import com.realdolmen.air.domain.AirlineCompany;
import com.realdolmen.air.domain.Airport;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Stateless
public class AirportRepository {
    @PersistenceContext
    EntityManager entityManager;

    public List<Airport> findAll(){
        return entityManager.createNamedQuery(Airport.FIND_ALL,Airport.class).getResultList();
    }

    public Airport findById(Long id){
        return entityManager.find(Airport.class, id);
    }
}
