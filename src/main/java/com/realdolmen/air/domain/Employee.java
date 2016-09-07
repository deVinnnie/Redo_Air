package com.realdolmen.air.domain;

import javax.persistence.Entity;

@Entity
public class Employee extends User{

    public Employee() {
        super(Role.EMPLOYEE);
    }
}
