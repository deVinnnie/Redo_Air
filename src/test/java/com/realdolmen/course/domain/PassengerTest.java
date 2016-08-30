package com.realdolmen.course.domain;

import com.realdolmen.course.utilities.persistence.JpaPersistenceTest;
import org.junit.Before;
import org.junit.Test;

import javax.persistence.EntityManager;
import javax.transaction.Transactional;
import javax.validation.*;
import java.lang.reflect.Array;
import java.util.*;



public class PassengerTest extends JpaPersistenceTest {

    private Validator validator;
    private Passenger passenger;

    @Before
    public void setUp(){
        ValidatorFactory factory =Validation.buildDefaultValidatorFactory();
        this.validator = factory.getValidator();

        this.passenger = new Passenger();

        PassengerId id = new PassengerId();
        id.setSsn("1236546789");
        id.setLastName("Duck");

        passenger.setId(id);
        passenger.setFirstName("Donald");
        passenger.setAddress(new Address("Quackel Street", "45", "DuckTown", "3150", "DuckPond"));
        passenger.setType(PassengerType.OCCASIONAL);
        passenger.setDateOfBirth(Calendar.getInstance().getTime());
        passenger.setCards(
                Arrays.asList(
                        new CreditCard("1234567", "2017-01-01", 123, CreditCardType.VISA),
                        new CreditCard("1234456567", "2017-01-05", 123, CreditCardType.MASTER)
                )
        );
        passenger.setPreferences(
                Arrays.asList(
                        "Coffee",
                        "Sugar",
                        "Milk"
                )
        );
        this.entityManager().persist(passenger);

    }
/*
    @Test
    public void testFirstNameLessThan50CharsIsValid(){
        passenger.setFirstName("Vincent");
        Set<ConstraintViolation<Passenger>> violations = validator.validate(passenger);
        assertEquals(0, violations.size());
    }

    @Test
    public void testFirstNameLargerThan50CharsIsNotValid(){
        passenger.setFirstName("SupercalifragilisticexpialidociousSuperRidicoulousVeryLongStupidBadNotGoodEvilName");
        Set<ConstraintViolation<Passenger>> violations = validator.validate(passenger);
        assertEquals(1, violations.size());
    }

    @Test
    public void testLastNameLessThan50CharsIsValid(){
        passenger.setLastName("Vincent");
        Set<ConstraintViolation<Passenger>> violations = validator.validate(passenger);
        assertEquals(0, violations.size());
    }

    @Test
    public void testLastNameLargerThan50CharsIsNotValid(){
        passenger.setLastName("SupercalifragilisticexpialidociousSuperRidicoulousVeryLongStupidBadNotGoodEvilName");
        Set<ConstraintViolation<Passenger>> violations = validator.validate(passenger);
        assertEquals(1, violations.size());
    }

    @Test
    public void testDateOfBirthInPastIsValid(){
        Calendar c = Calendar.getInstance();
        c.set(Calendar.YEAR, 1992);
        c.set(Calendar.MONTH, 5);
        c.set(Calendar.DAY_OF_MONTH, 6);

        Date birthDate = c.getTime();

        passenger.setDateOfBirth(birthDate);
        Set<ConstraintViolation<Passenger>> violations = validator.validate(passenger);

        assertEquals(0, violations.size());
    }

    @Test
    public void testDateOfBirthInFutureIsInValid(){
        Calendar c = Calendar.getInstance();
        int currentYear = c.get(Calendar.YEAR);
        int futureYear = currentYear + 10;
        c.set(Calendar.YEAR, futureYear);
        c.set(Calendar.MONTH, 5);
        c.set(Calendar.DAY_OF_MONTH, 6);
        Date birthDate = c.getTime();

        passenger.setDateOfBirth(birthDate);
        Set<ConstraintViolation<Passenger>> violations = validator.validate(passenger);

        // Age will be less than 0.
        // Date is in future.
        // Gives two constraint violations.
        assertEquals(2, violations.size());
    }

    @Test
    public void testPassengerTypeEqualToRegularOrOccasionalIsValid(){
        Set<ConstraintViolation<Passenger>> violations;
        passenger.setPassengerType("Regular");
        violations = validator.validate(passenger);
        assertEquals(0, violations.size());

        passenger.setPassengerType("Occasional");
        violations = validator.validate(passenger);
        assertEquals(0, violations.size());
    }

    @Test
    public void testPassengerTypeEqualToRubbishIsNotValid(){
        passenger.setPassengerType("Blablabla");

        Set<ConstraintViolation<Passenger>> violations = validator.validate(passenger);
        assertEquals(1, violations.size());
    }

    @Test
    public void testSSNEqualToNullIsNotValid(){
        Set<ConstraintViolation<Passenger>> violations;
        passenger.setSsn(null);
        violations = validator.validate(passenger);
        assertEquals(1, violations.size());
    }
*/

    @Test
    public void testPersistence(){
        EntityManager em = this.entityManager();

        Passenger p2 = new Passenger();
        PassengerId id2 = new PassengerId();
        id2.setSsn("123654642");
        id2.setLastName("Potter");

        p2.setId(id2);

        p2.setFirstName("Harry");
        p2.setAddress(new Address("Privet Drive", "4", "London", "3000", "United Kingdom"));
        p2.setType(PassengerType.REGULAR);
        p2.setCards(
                Arrays.asList(
                        new CreditCard("12348567", "2017-01-01", 15, CreditCardType.VISA),
                        new CreditCard("734456567", "2017-01-05", 1248, CreditCardType.MASTER)
                )
        );
        p2.setPreferences(
                Arrays.asList(
                        "Broomstick",
                        "Magic Wand",
                        "Owl"
                )
        );

        em.persist(p2);

        PassengerId dbid1 = passenger.getId();
        PassengerId dbid2 = p2.getId();

        em.flush();
        em.clear();

        Passenger fdb1 = em.find(Passenger.class, dbid1);
        Passenger fdb2 =  em.find(Passenger.class, dbid2);

        assertEquals("Donald", fdb1.getFirstName());
        assertEquals("Duck", fdb1.getId().getLastName());

        //Passenger p2 = em.find(Passenger.class, id);
        // System.out.println(p2.getFirstName());
    }

    @Test
    public void testTicket(){
        Flight outFlight = new DomesticFlight("ASE-4254",
                                        Calendar.getInstance().getTime(),
                                        Calendar.getInstance().getTime()
                );

        entityManager().persist(outFlight);

        Flight returnFlight = new DomesticFlight("USA-1338",
                Calendar.getInstance().getTime(),
                Calendar.getInstance().getTime()
        );

        entityManager().persist(returnFlight);


        Ticket ticket = new Ticket(30.3, passenger, outFlight, returnFlight);
        entityManager().persist(ticket);
    }
}
