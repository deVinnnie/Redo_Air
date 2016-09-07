package com.realdolmen.air.service;

import com.realdolmen.air.domain.Customer;
import com.realdolmen.air.repository.CustomerRepositoryInterface;

import javax.ejb.EJB;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import java.util.List;

@Stateless
@LocalBean
public class CustomerServiceBean {
    @EJB
    CustomerRepositoryInterface customerRepository;

    public Customer createCustomer(Customer customer){
        return customerRepository.createCustomer(customer);
    }
    public List<Customer> findCustomerByEmail(String email){
        return customerRepository.findCustomerByEmail(email);
    }
}
