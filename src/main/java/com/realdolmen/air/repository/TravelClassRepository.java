package com.realdolmen.air.repository;

import com.realdolmen.air.domain.Flight;
import com.realdolmen.air.domain.TravelClass;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Stateless
public class TravelClassRepository {

    @PersistenceContext
    EntityManager em;

    public TravelClass update(TravelClass travelClass){
        travelClass = em.merge(travelClass);
        return travelClass;
    }

    public TravelClass find(Long travelClassId) {
        return em.find(TravelClass.class, travelClassId);
    }
}
