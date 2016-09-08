package com.realdolmen.air.beans;

import com.realdolmen.air.domain.Customer;
import com.realdolmen.air.domain.Employee;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertSame;

public class UserBeanTest {

    private UserBean bean;

    @Before
    public void setUp(){
        bean = new UserBean();
    }
    
    @Test
    public void getUserAsEmployeeShouldReturnEmployeeWhenUserIsAnEmployeeInstance(){
        Employee employee = new Employee();
        employee.setEmail("river.tam@redo-air.com");
        employee.setPassword("hash-of-river123");

        bean.setUser(employee);

        Employee userAsEmployee = bean.getUserAsEmployee();
        assertNotNull(userAsEmployee);
        assertSame(employee, userAsEmployee);
    }

    @Test
    public void getUserAsEmployeeShouldReturnNullWhenUserIsNotAnEmployeeInstance(){
        Customer customer = new Customer();
        customer.setEmail("albus.dumbledore@hogwarts.org");
        customer.setPassword("hash-of-grindelwald123");

        bean.setUser(customer);

        Employee userAsEmployee = bean.getUserAsEmployee();
        assertNull(userAsEmployee);
    }

    @Test
    public void getUserAsCustomerShouldReturnCustomerWhenUserIsACustomerInstance(){
        Customer customer = new Customer();
        customer.setEmail("albus.dumbledore@hogwarts.org");
        customer.setPassword("hash-of-grindelwald123");

        bean.setUser(customer);

        Customer userAsCustomer = bean.getUserAsCustomer();
        assertNotNull(userAsCustomer);
        assertSame(customer, userAsCustomer);
    }

    @Test
    public void getUserAsCustomerShouldReturnNullWhenUserIsNotACustomerInstance(){
        Employee employee = new Employee();
        employee.setEmail("river.tam@redo-air.com");
        employee.setPassword("hash-of-river123");

        bean.setUser(employee);

        Customer userAsCustomer = bean.getUserAsCustomer();
        assertNull(userAsCustomer);
    }

    @Test
    public void getUserAsCustomerShouldReturnNullWhenUserIsNull(){
        bean.setUser(null);

        Customer userAsCustomer = bean.getUserAsCustomer();
        assertNull(userAsCustomer);
    }

    @Test
    public void getUserAsEmployeeShouldReturnNullWhenUserIsNull(){
        bean.setUser(null);

        Employee userAsEmployee = bean.getUserAsEmployee();
        assertNull(userAsEmployee);
    }
}
