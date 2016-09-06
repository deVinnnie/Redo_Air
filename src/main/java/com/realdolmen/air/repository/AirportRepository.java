package com.realdolmen.air.repository;

import com.realdolmen.air.domain.Airport;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import java.util.Collections;
import java.util.List;

@Stateless
public class AirportRepository extends AbstractBaseRepository<Airport, Long> implements AirportRepositoryInterface {
    private static final Logger LOGGER = LoggerFactory.getLogger(FlightRepository.class);

    @Override
    public List<Airport> findAll() {
        try {
            return getEntityManager().createNamedQuery(Airport.FIND_ALL_ACTIVE, Airport.class).getResultList();
        } catch (NoResultException e) {
            LOGGER.error("No information found: ", e);
        }
        return Collections.emptyList();
    }

    public List<Airport> findAllActiveAndNonActive() {
        try {
            return getEntityManager().createNamedQuery(Airport.FIND_ALL, Airport.class).getResultList();
        } catch (NoResultException e) {
            LOGGER.error("No information found: ", e);
        }
        return Collections.emptyList();
    }


    @Override
    public Airport findById(Long id) {
        try {
            return getEntityManager().find(Airport.class, id);
        } catch (NoResultException e) {
            LOGGER.error("No information found: ", e);
        }
        return null;
    }

    /**
     * Return all airports where the searchterm matches the airport code or name.
     *
     * @return List of matching airports.
     */
    @Override
    public List<Airport> search(String searchTerm) {
        try {
            return getEntityManager()
                    .createQuery("SELECT a FROM Airport a WHERE a.name LIKE :search OR a.code LIKE :search", Airport.class)
                    .setParameter("search", "%" + searchTerm.toLowerCase() + "%")
                    .getResultList();
        } catch (NoResultException e) {
            LOGGER.error("No information found: ", e);
        }
        return Collections.emptyList();

    }
}
