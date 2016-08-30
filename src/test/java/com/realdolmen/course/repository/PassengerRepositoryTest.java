package com.realdolmen.course.repository;


import com.realdolmen.course.domain.Passenger;
import com.realdolmen.course.domain.PassengerId;
import com.realdolmen.course.domain.PassengerType;
import com.realdolmen.course.utilities.persistence.JpaPersistenceTest;
import org.junit.Before;
import org.junit.Test;

import java.util.Calendar;

public class PassengerRepositoryTest extends JpaPersistenceTest{

    PassengerRepository passengerRepository;

    private static final PassengerId TEST_PASSENGER_ID = new PassengerId("456487938", "Potter");


    @Before
    public void initializeRepository() {
        passengerRepository = new PassengerRepository();
        passengerRepository.em = entityManager();
    }

    @Test
    public void shouldSaveAPassenger() {
        Passenger passenger = new Passenger(
                new PassengerId("1515", "Duck"),
                "Donald",
                Calendar.getInstance().getTime(),
                PassengerType.OCCASIONAL
        );

        passengerRepository.save(passenger);

        assertNotNull("Person ID is not supposed to be null after saving", passenger.getId());
    }

    @Test
    public void testFindByIdReturnsCorrectEntity(){
        Passenger passenger = passengerRepository.findById(TEST_PASSENGER_ID);

        assertNotNull(passenger);

        assertEquals("Harry", passenger.getFirstName());
        assertEquals("Privet Drive", passenger.getAddress().getStreet1());
    }

    @Test
    public void testDeletePassenger(){
        Passenger passenger;
        int size;

        size = entityManager().createNamedQuery(Passenger.FIND_ALL).getResultList().size();
        assertEquals(3, size);

        passenger = passengerRepository.findById(TEST_PASSENGER_ID);
        assertNotNull(passenger);

        entityManager().flush();
        entityManager().clear();

        passengerRepository.remove(passenger);

        size = entityManager().createNamedQuery(Passenger.FIND_ALL).getResultList().size();
        assertEquals(2, size);

        passenger = passengerRepository.findById(TEST_PASSENGER_ID);
        assertNull(passenger);
    }
}
