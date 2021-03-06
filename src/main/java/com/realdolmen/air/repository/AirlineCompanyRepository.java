package com.realdolmen.air.repository;

import com.realdolmen.air.domain.AirlineCompany;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.ejb.Stateless;
import javax.persistence.NoResultException;
import java.util.Collections;
import java.util.List;

@Stateless
public class AirlineCompanyRepository extends AbstractBaseRepository<AirlineCompany, Long> implements AirlineCompanyRepositoryInterface {
    private static final Logger LOGGER = LoggerFactory.getLogger(FlightRepository.class);
    private static String info = "No information found: ";

    @Override
    public List<AirlineCompany> findAll() {
        try {
            return getEntityManager().createNamedQuery(AirlineCompany.FIND_ALL, AirlineCompany.class).getResultList();
        } catch (NoResultException e) {
            LOGGER.error(info, e);
        }
        return Collections.emptyList();
    }

    @Override
    public AirlineCompany findById(Long id) {
        try {
            return getEntityManager().find(AirlineCompany.class, id);
        } catch (NoResultException e) {
            LOGGER.error(info, e);
        }
        return null;
    }

    @Override
    public List<AirlineCompany> findAirlineCompaniesSearch(String searchTerm) {
        try {
            return getEntityManager().createNamedQuery(AirlineCompany.FIND_ALL_SEARCH, AirlineCompany.class).setParameter("searchString", "%" + searchTerm.toLowerCase() + "%").getResultList();
        } catch (NoResultException e) {
            LOGGER.error(info, e);
        }
        return Collections.emptyList();
    }

    @Override
    public List<AirlineCompany> findAllActive() {
        try {
            return getEntityManager().createNamedQuery(AirlineCompany.FIND_ALL_ACTIVE, AirlineCompany.class).getResultList();
        } catch (NoResultException e) {
            LOGGER.error(info, e);
        }
        return Collections.emptyList();
    }
}
