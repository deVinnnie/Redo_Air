package com.realdolmen.air.service;

import com.realdolmen.air.domain.Customer;
import com.realdolmen.air.repository.CustomerRepository;

import javax.ejb.EJB;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;

@Stateless
@LocalBean
public class CustomerServiceBean {
    @EJB
    CustomerRepository customerRepository;

    public Customer createCustomer(Customer customer){
        return customerRepository.createCustomer(customer);
    }
    public Customer findCustomerByEmail(String email){
        return customerRepository.findCustomerByEmail(email);
    }
}
