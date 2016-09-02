package com.realdolmen.air.repository;

import com.realdolmen.air.domain.AirlineCompany;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Stateless
public class AirlineCompanyRepository {
    @PersistenceContext
    EntityManager entityManager;

    public List<AirlineCompany> findAll(){
        return entityManager.createNamedQuery(AirlineCompany.FIND_ALL,AirlineCompany.class).getResultList();
    }

    public AirlineCompany findByName(String name){
        return entityManager.createNamedQuery(AirlineCompany.FIND_BY_NAME, AirlineCompany.class).setParameter("name",name).getSingleResult();
    }
}
