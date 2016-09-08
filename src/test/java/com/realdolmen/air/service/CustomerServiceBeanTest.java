package com.realdolmen.air.service;

import com.realdolmen.air.domain.AirlineCompany;
import com.realdolmen.air.domain.Customer;
import com.realdolmen.air.repository.AirlineCompanyRepositoryInterface;
import com.realdolmen.air.repository.CustomerRepositoryInterface;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class CustomerServiceBeanTest {
    @Mock
    CustomerRepositoryInterface customerRepositoryInterface;

    @InjectMocks
    private CustomerServiceBean customerServiceBean = new CustomerServiceBean();

    private Customer customer;
    private List<Customer> customers;

    @Before
    public void setupMox() {
        customer = new Customer();
        customer.setId(1L);

        customers = new ArrayList<>();
        customers.add(customer);

        when(customerRepositoryInterface.findCustomerByEmail("email")).thenReturn(customers);
        when(customerRepositoryInterface.createCustomer(customer)).thenReturn(customer);
    }


    @Test
    public void test_getCustomerByEmailOnlyPerformsOneAction(){
        List<Customer> results = customerServiceBean.findCustomerByEmail("email");

        assertNotNull(results);
        assertEquals(1, results.size());

        verify(customerRepositoryInterface).findCustomerByEmail("email");
        verifyNoMoreInteractions(customerRepositoryInterface);
    }

    @Test
    public void test_getCustomerByNonExistingEmailOnlyPerformsOneActionAndReturnsAnEmptyList(){
        List<Customer> results = customerServiceBean.findCustomerByEmail("email2");

        assertNotNull(results);
        assertEquals(0, results.size());

        verify(customerRepositoryInterface).findCustomerByEmail("email2");
        verifyNoMoreInteractions(customerRepositoryInterface);
    }

    @Test
    public void test_createCustomerOnlyPerformsOneAction(){
        Customer customerAfterCreate = customerServiceBean.createCustomer(customer);
        assertNotNull(customerAfterCreate);
        assertNotNull(customerAfterCreate.getId());

        Long expected = 1L;
        assertEquals(expected, customerAfterCreate.getId());

        verify(customerRepositoryInterface).createCustomer(customer);
        verifyNoMoreInteractions(customerRepositoryInterface);
    }
}
