package com.realdolmen.air.repository;

import com.realdolmen.air.domain.TravelClass;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.ejb.Stateless;
import javax.persistence.NoResultException;
import java.util.Collections;
import java.util.List;

@Stateless
public class TravelClassRepository extends AbstractBaseRepository<TravelClass, Long> implements TravelClassRepositoryInterface {
    private static final Logger LOGGER = LoggerFactory.getLogger(FlightRepository.class);

    @Override
    public TravelClass find(Long travelClassId) {
        try {
            return getEntityManager().find(TravelClass.class, travelClassId);
        } catch (NoResultException e) {
            LOGGER.error("No information found: ", e);
        }
        return null;
    }

    @Override
    public TravelClass update(TravelClass travelClass) {
        return getEntityManager().merge(travelClass);
    }

    @Override
    public List<TravelClass> findAllTravelClassesOfAFlight(Long flightId, int numberOfSeats) {
        try{
            return getEntityManager().createNamedQuery(TravelClass.FIND_CLASSES_FROM_FLIGHT, TravelClass.class)
                    .setParameter("numberOfSeats", numberOfSeats)
                    .setParameter("flightId", flightId)
                    .getResultList();
        } catch (NoResultException e) {
            LOGGER.error("No information found: ", e);
        }
        return Collections.emptyList();

    }
}
