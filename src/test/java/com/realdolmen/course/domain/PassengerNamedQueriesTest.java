package com.realdolmen.course.domain;

import com.realdolmen.course.utilities.persistence.JpaPersistenceTest;
import org.junit.Before;
import org.junit.Test;

import javax.persistence.EntityManager;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import java.util.Arrays;
import java.util.Calendar;
import java.util.List;

public class PassengerNamedQueriesTest extends JpaPersistenceTest {

    private static final PassengerId TEST_PASSENGER_ID_1 = new PassengerId("456487938","Potter");
    private static final PassengerId TEST_PASSENGER_ID_2 = new PassengerId("457864818","Dumbledore");

    @Test
    public void testNamedQueryFindAll(){
        List<Passenger> allPassengers = entityManager().createNamedQuery(Passenger.FIND_ALL, Passenger.class)
                       .getResultList();

        assertEquals(3, allPassengers.size());
    }

    @Test
    public void testNamedQueryFindAllLastNames(){
        List<String> lastNames = entityManager().createNamedQuery(Passenger.FIND_ALL_LAST_NAMES, String.class)
                                                .getResultList();
        assertEquals(3, lastNames.size());
        assertTrue(lastNames.contains("Dumbledore"));
        assertTrue(lastNames.contains("Potter"));
        assertTrue(lastNames.contains("Black"));
    }

    @Test
    public void testNamedQueryTotalFrequentFlyerMiles(){
        Long total;
        total = entityManager().createNamedQuery(Passenger.TOTAL_FREQUENT_FLYER_MILES, Long.class)
                                       .getSingleResult();

        assertNotNull(total);
        assertEquals(0, (long) total);

        Passenger passenger1 = entityManager().find(Passenger.class, TEST_PASSENGER_ID_1);
        passenger1.setFrequentFlyerMiles(12);

        Passenger passenger2 = entityManager().find(Passenger.class, TEST_PASSENGER_ID_2);
        passenger2.setFrequentFlyerMiles(1);


        total = entityManager().createNamedQuery(Passenger.TOTAL_FREQUENT_FLYER_MILES, Long.class)
                .getSingleResult();
        assertNotNull(total);
        assertEquals(13, (long) total);
    }

    @Test
    public void testNamedQueryFindTicketsByPassengerId(){
        List<Ticket> ticketsForHarryPotter;
        ticketsForHarryPotter = entityManager()
                .createNamedQuery(Passenger.FIND_TICKETS_BY_PASSENGER_ID)
                .setParameter("id", TEST_PASSENGER_ID_1)
                .getResultList();

        assertEquals(0, ticketsForHarryPotter.size());

        Passenger harryPotter = entityManager().find(Passenger.class, TEST_PASSENGER_ID_1);
        Ticket ticket = new Ticket(42.5, harryPotter, null, null);

        entityManager().persist(ticket);

        ticketsForHarryPotter = entityManager()
                        .createNamedQuery(Passenger.FIND_TICKETS_BY_PASSENGER_ID)
                        .setParameter("id", TEST_PASSENGER_ID_1)
                        .getResultList();

        assertEquals(1, ticketsForHarryPotter.size());
    }

    @Test
    public void testNamedQueryDeleteAll(){
        Passenger passenger = entityManager().find(Passenger.class, TEST_PASSENGER_ID_1);
        Ticket ticket = new Ticket(48.5, passenger, null,null);

        entityManager().persist(ticket);

        assertTrue(entityManager().createNamedQuery(Passenger.FIND_ALL).getResultList().size() > 0);

        entityManager().createNamedQuery(Passenger.DELETE_ALL).executeUpdate();

        assertEquals(1, entityManager().createNamedQuery(Passenger.FIND_ALL).getResultList().size());
    }
}
