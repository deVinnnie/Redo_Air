package com.realdolmen.air.repository;

import com.realdolmen.air.domain.Airport;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Stateless
public class AirportRepository {

    @PersistenceContext
    private EntityManager entityManager;

    public List<Airport> findAll() {
        return entityManager.createNamedQuery(Airport.FIND_ALL, Airport.class).getResultList();
    }

    public Airport findById(Long id) {
        return entityManager.find(Airport.class, id);
    }

    /**
     * Return all airports where the searchterm matches the airport code or name.
     *
     * @return List of matching airports.
     */
    public List<Airport> search(String searchTerm) {
        List<Airport> airports = entityManager
                .createQuery("SELECT a FROM Airport a WHERE a.name LIKE :search OR a.code LIKE :search", Airport.class)
                .setParameter("search", "%" + searchTerm + "%")
                .getResultList();
        return airports;
    }

    public EntityManager getEntityManager() {
        return entityManager;
    }

    public void setEntityManager(EntityManager entityManager) {
        this.entityManager = entityManager;
    }
}
