package com.realdolmen.air.repository;

import com.realdolmen.air.domain.Airport;
import com.realdolmen.air.domain.Customer;
import com.realdolmen.testutil.TestData;
import com.realdolmen.testutil.TestDataLocation;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.assertEquals;

/**
 * Created by knockaert on 5/04/2016.
 */
public class CustomerRepositoryTest extends AbstractRepositoryTest<CustomerRepository> {
    @Test
    @TestData(dataSet = TestDataLocation.CUSTOMER)
    public void findByExistingEmailShouldReturnCorrectCustomer() {
        List<Customer> customer = getRepository().findCustomerByEmail("email1");
        Long expectedId = Long.parseLong("1");
        assertEquals(expectedId, customer.get(0).getId());
    }

    @Test
    @TestData(dataSet = TestDataLocation.CUSTOMER)
    public void findByNonExistingEmailShouldReturnNoCustomer() {
        List<Customer> customer = getRepository().findCustomerByEmail("Vincent is de beste");
        assertEquals(null, customer);
    }

    @Test
    @TestData(dataSet = TestDataLocation.AIRPORT)
    public void createCustomerShouldReturnACustomer(){
        Customer customer = new Customer();
        Customer customer1 = getRepository().createCustomer(customer);
        assertEquals(customer, customer1);
    }
}
