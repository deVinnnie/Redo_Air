package com.realdolmen.air.repository;

import com.realdolmen.air.domain.Flight;
import com.realdolmen.air.domain.TravelClass;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Stateless
public class FlightRepository {

    @PersistenceContext
    private EntityManager entityManager;

    public Flight findById(Long id){
        return entityManager.find(Flight.class, id);
    }

    public List<TravelClass> findTravelClasses(Long flightId){
        return entityManager.createQuery("SELECT t FROM TravelClass t WHERE t.flight.id = :flight", TravelClass.class)
                .setParameter("flight", flightId)
                .getResultList();
    }

    public Flight update(Flight flight){
        System.out.println("Flight: " + flight.getId());
        flight = entityManager.merge(flight);


        System.out.println("Flight: " + flight.getId());
        return flight;
    }

    public List<Flight> findAll(){
        return entityManager.createNamedQuery(Flight.FIND_ALL, Flight.class).getResultList();
    }
}
