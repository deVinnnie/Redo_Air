package com.realdolmen.air.repository;

import com.realdolmen.air.domain.Customer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

@Stateless
public class CustomerRepository extends AbstractBaseRepository<Customer, Long> implements CustomerRepositoryInterface{
    private static final Logger LOGGER = LoggerFactory.getLogger(FlightRepository.class);

    public Customer createCustomer(Customer customer){
        getEntityManager().persist(customer);
        return customer;
    }

    public Customer findCustomerByEmail(String email){
        Customer customer = null;
        try{
            TypedQuery<Customer> query = getEntityManager().createNamedQuery(Customer.FIND_BY_EMAIL, Customer.class);
            query.setParameter("email",email);
            customer = query.getSingleResult();
            return customer;
        }catch (NoResultException e){
            LOGGER.error("No information found: ", e);
        }
        return null;
    }
}
