package com.realdolmen.air.repository;

import com.realdolmen.air.domain.TravelClass;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

public interface TravelClassRepositoryInterface {
    TravelClass update(TravelClass travelClass);
    TravelClass find(Long travelClassId);
    List<TravelClass> findAllTravelClassesOfAFlight(Long flightId, int numberOfSeats);
}
