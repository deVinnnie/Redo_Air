package com.realdolmen.air.domain;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class EmployeeTest {

    @Test
    public void defaultConstructorShouldSetRoleToCustomer(){
        Employee employee = new Employee();
        assertEquals(Role.EMPLOYEE, employee.getRole());
    }
}