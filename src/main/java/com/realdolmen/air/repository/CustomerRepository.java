package com.realdolmen.air.repository;

import com.realdolmen.air.domain.Customer;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

@Stateless
public class CustomerRepository {
    @PersistenceContext
    EntityManager entityManager;

    public Customer createCustomer(Customer customer){
        entityManager.persist(customer);
        return customer;
    }

    public Customer findCustomerByEmail(String email){
        Customer customer = null;
        try{
            TypedQuery<Customer> query = entityManager.createNamedQuery(Customer.FIND_BY_EMAIL, Customer.class);
            query.setParameter("email",email);
            customer = query.getSingleResult();
        }catch (NoResultException e){
            e.printStackTrace();
        }

        return customer;
    }
}
