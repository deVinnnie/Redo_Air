package com.realdolmen.air.repository;

import com.realdolmen.air.domain.Flight;
import com.realdolmen.air.domain.TravelClass;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.List;

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

    public List<TravelClass> findAllTravelClassesOfAFlight(Long flightId, int numberOfSeats){
        return em.createNamedQuery(TravelClass.FIND_CLASSES_FROM_FLIGHT, TravelClass.class)
                .setParameter("numberOfSeats",numberOfSeats)
                .setParameter("flightId",flightId)
                .getResultList();
    }
}
