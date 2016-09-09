package com.realdolmen.air.repository;

import com.realdolmen.air.domain.Customer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.ejb.Stateless;
import javax.persistence.TypedQuery;
import java.util.Collections;
import java.util.List;

@Stateless
public class CustomerRepository extends AbstractBaseRepository<Customer, Long> implements CustomerRepositoryInterface{
    private static final Logger LOGGER = LoggerFactory.getLogger(FlightRepository.class);

    @Override
    public Customer createCustomer(Customer customer){
        getEntityManager().persist(customer);
        return customer;
    }

    @Override
    public List<Customer> findCustomerByEmail(String email){
        try{
            TypedQuery<Customer> query = getEntityManager().createNamedQuery(Customer.FIND_BY_EMAIL2, Customer.class);
            query.setParameter("email",email);
            return query.getResultList();
        }catch (Exception e){
            LOGGER.error("No information found: ", e);
        }
        return Collections.emptyList();
    }
}
