package com.realdolmen.course.service;

import com.realdolmen.course.domain.Person;

import javax.ejb.Remote;
import java.util.List;

@Remote
public interface PersonServiceRemote {
    Person save(Person person);
    Person findById(Long id);
    List<Person> findAll();
    void remove(long personId);
}
