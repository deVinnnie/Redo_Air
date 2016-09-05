package com.realdolmen.air.repository;

import com.realdolmen.air.domain.Flight;
import com.realdolmen.air.domain.TravelClass;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import java.util.Collections;
import java.util.Date;
import java.util.List;

@Stateless
public class FlightRepository {
    private static final Logger LOGGER = LoggerFactory.getLogger(FlightRepository.class);

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
        try{
            return entityManager.createNamedQuery(Flight.FIND_ALL, Flight.class).getResultList();
        }catch (NoResultException e){
            LOGGER.error("No information found: ",e);
        }
        return Collections.emptyList();
    }

    public List<Flight> findFlightsWithParams(Long airlineCompanyId, String flightClass, int numberOfSeats, Long departureAirportId, Long arrivalAirportId){
        try{
            return entityManager.createNamedQuery(Flight.FIND_SEARCH, Flight.class)
                    .setParameter("airlineCompanyId", airlineCompanyId)
                    .setParameter("flightClass", flightClass)
                    .setParameter("numberOfSeats", numberOfSeats)
                    .setParameter("departureAirportId", departureAirportId)
                    .setParameter("arrivalAirportId", arrivalAirportId)
                    .getResultList();
        }catch (NoResultException e){
            LOGGER.error("No information found: ",e);
        }
        return Collections.emptyList();
    }
}
