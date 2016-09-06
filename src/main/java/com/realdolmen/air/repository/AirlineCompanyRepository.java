package com.realdolmen.air.repository;

import com.realdolmen.air.domain.AirlineCompany;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import java.util.Collections;
import java.util.List;

@Stateless
public class AirlineCompanyRepository extends AbstractBaseRepository<AirlineCompany, Long> implements AirlineCompanyRepositoryInterface {
    private static final Logger LOGGER = LoggerFactory.getLogger(FlightRepository.class);

    @Override
    public List<AirlineCompany> findAll() {
        try {
            return getEntityManager().createNamedQuery(AirlineCompany.FIND_ALL, AirlineCompany.class).getResultList();
        } catch (NoResultException e) {
            LOGGER.error("No information found: ", e);
        }
        return Collections.emptyList();
    }

    @Override
    public AirlineCompany findById(Long id) {
        try {
            return getEntityManager().find(AirlineCompany.class, id);
        } catch (NoResultException e) {
            LOGGER.error("No information found: ", e);
        }
        return null;
    }
}
