package com.realdolmen.course.service;

import com.realdolmen.course.domain.Person;
import com.realdolmen.course.repository.PersonRepository;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class PersonServiceTest {

    @Mock
    private PersonRepository repository;

    @InjectMocks
    private PersonServiceBean service = new PersonServiceBean();

    @Before
    public void setupMox() {
        Person person = new Person("Theo", "Tester");
        List<Person> persons = new ArrayList<>();
        persons.add(person);
        when(repository.findAll()).thenReturn(persons);
    }

    @After
    public void tearDown() throws Exception {
        verifyNoMoreInteractions(repository);
    }

    @Test
    public void shouldFindAllPersons() {
        List<Person> persons = service.findAll();
        assertNotNull(persons);
        assertFalse(persons.isEmpty());
        verify(repository).findAll();
    }

    @Test
    public void shouldCreateAPerson() {
        Person person = new Person("Theo", "Tester");
        service.save(person);
        verify(repository).save(same(person));
    }

    @Test
    public void shouldRemovePerson() throws Exception {
        service.remove(1507);
        verify(repository).remove(1507);
    }
}
