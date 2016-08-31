package com.realdolmen.air.service;

import com.realdolmen.air.domain.Customer;

import javax.ejb.Remote;

@Remote
public interface CustomerService {
    Customer createCustomer(Customer customer);
}
