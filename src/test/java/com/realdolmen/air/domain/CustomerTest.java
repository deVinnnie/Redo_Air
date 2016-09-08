package com.realdolmen.air.domain;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class CustomerTest {

    @Test
    public void defaultConstructorShouldSetRoleToCustomer(){
        Customer customer = new Customer();
        assertEquals(Role.CUSTOMER, customer.getRole());
    }

    @Test
    public void overloadedConstructorShouldSetRoleToCustomer(){
        Customer customer = new Customer("Zoe", "Washburne");
        assertEquals(Role.CUSTOMER, customer.getRole());
    }
}
