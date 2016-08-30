package com.realdolmen.course.service;

import com.realdolmen.course.domain.Passenger;
import com.realdolmen.course.domain.PassengerId;
import com.realdolmen.course.domain.PassengerType;
import com.realdolmen.course.domain.Person;
import com.realdolmen.course.repository.PassengerRepository;
import com.realdolmen.course.repository.PersonRepository;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.mockito.Matchers.same;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class PassengerEJBTest {

    @Mock
    private PassengerRepository repository;

    @InjectMocks
    private PassengerEJB service = new PassengerEJB();

    @Before
    public void setupMox() {
        Passenger passenger = new Passenger(
            new PassengerId("123", "Potter"),
                "Harry", new Date(), PassengerType.OCCASIONAL
        );

        List<Passenger> passengers = new ArrayList<>();
        passengers.add(passenger);
        when(repository.findAll()).thenReturn(passengers);
    }

    @After
    public void tearDown() throws Exception {
        //verifyNoMoreInteractions(repository);
    }

    @Test
    public void shouldFindAllPersons() {
        List<Passenger> passengers = service.findPassengers();
        assertNotNull(passengers);
        assertFalse(passengers.isEmpty());
        verify(repository).findAll();
    }

    @Test
    public void shouldCreateAPerson() {
        Passenger passenger = new Passenger(
                new PassengerId("123", "Dumbledore"),
                "Albus", new Date(), PassengerType.OCCASIONAL
        );
        service.createPassenger(passenger);
        verify(repository).save(same(passenger));
    }

    @Test
    public void shouldRemovePerson() throws Exception {
        PassengerId id = new PassengerId("123", "Potter");
        Passenger passenger = service.findPassengerById(id);
        service.deletePassenger(passenger);
        verify(repository).remove(passenger);
    }
}
