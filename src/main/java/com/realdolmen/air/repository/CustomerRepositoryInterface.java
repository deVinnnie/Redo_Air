package com.realdolmen.air.repository;

import com.realdolmen.air.domain.Customer;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

public interface CustomerRepositoryInterface extends Repository<Customer, Long>{
    Customer createCustomer(Customer customer);
    Customer findCustomerByEmail(String email);
}
