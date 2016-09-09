package com.realdolmen.air.repository;

import com.realdolmen.air.domain.Flight;
import com.realdolmen.air.domain.TravelClass;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.ejb.Stateless;
import javax.persistence.NoResultException;
import java.util.Collections;
import java.util.List;

@Stateless
public class FlightRepository extends AbstractBaseRepository<Flight, Long> implements FlightRepositoryInterface {
    private static final Logger LOGGER = LoggerFactory.getLogger(FlightRepository.class);
    private static String info = "No information found: ";

    @Override
    public Flight findById(Long id) {
        try {
            return getEntityManager().find(Flight.class, id);
        } catch (NoResultException e) {
            LOGGER.error(info, e);
        }
        return null;
    }

    @Override
    public List<TravelClass> findTravelClasses(Long flightId) {
        try {
            return getEntityManager().createQuery("SELECT t FROM TravelClass t WHERE t.flight.id = :flight", TravelClass.class)
                    .setParameter("flight", flightId)
                    .getResultList();
        } catch (NoResultException e) {
            LOGGER.error(info, e);
        }
        return Collections.emptyList();
    }

    @Override
    public Flight update(Flight flight) {
        return getEntityManager().merge(flight);
    }

    @Override
    public List<Flight> findAll() {
        try {
            return getEntityManager().createNamedQuery(Flight.FIND_ALL, Flight.class).getResultList();
        } catch (NoResultException e) {
            LOGGER.error(info, e);
        }
        return Collections.emptyList();
    }

    @Override
    public List<Flight> findFlightsWithParams(Long airlineCompanyId, String flightClass, int numberOfSeats, Long departureAirportId, Long arrivalAirportId) {
        try {
            return getEntityManager().createNamedQuery(Flight.FIND_SEARCH, Flight.class)
                    .setParameter("airlineCompanyId", airlineCompanyId)
                    .setParameter("flightClass", flightClass)
                    .setParameter("numberOfSeats", numberOfSeats)
                    .setParameter("departureAirportId", departureAirportId)
                    .setParameter("arrivalAirportId", arrivalAirportId)
                    .getResultList();
        } catch (NoResultException e) {
            LOGGER.error(info, e);
        }
        return Collections.emptyList();
    }
}
