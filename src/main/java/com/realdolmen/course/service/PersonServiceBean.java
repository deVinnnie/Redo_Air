package com.realdolmen.course.service;

import com.realdolmen.course.domain.Person;
import com.realdolmen.course.repository.PersonRepository;

import javax.ejb.EJB;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import java.util.List;

@Stateless
@LocalBean
public class PersonServiceBean implements PersonServiceRemote{
    @EJB
    PersonRepository personRepository;

    @Override
    public Person save(Person person) {
        return personRepository.save(person);
    }

    @Override
    public Person findById(Long id) {
        return personRepository.findById(id);
    }

    @Override
    public List<Person> findAll() {
        return personRepository.findAll();
    }

    @Override
    public void remove(long personId) {
        personRepository.remove(personId);
    }
}
