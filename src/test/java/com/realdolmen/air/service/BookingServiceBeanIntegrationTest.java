package com.realdolmen.air.service;

import com.realdolmen.air.domain.Customer;
import com.realdolmen.air.domain.Passenger;
import com.realdolmen.air.domain.payement.Endorsement;
import com.realdolmen.util.integration.RemoteIntegrationTest;
import org.junit.Test;

import javax.ejb.EJBTransactionRolledbackException;
import java.util.Arrays;

public class BookingServiceBeanIntegrationTest extends RemoteIntegrationTest {

    private Customer customer1 = new Customer("River", "Tam");
    private Customer customer2 = new Customer("Simon", "Tam");

    @Test(expected = EJBTransactionRolledbackException.class)
    public void testGetAllAirports() throws Exception {
        BookingService service1 = lookup("quickstart/BookingServiceBean!com.realdolmen.air.service.BookingService");

        BookingService service2 = lookup("quickstart/BookingServiceBean!com.realdolmen.air.service.BookingService");

        Customer customer1 = service1.findCustomerById(10L);
        assertEquals(5, (long) service1.findTravelClass(200200L).getRemainingSeats());

        service1.setCustomer(customer1);
        service1.setNumberOfSeats(5);
        service1.setTravelClass(
                service1.findTravelClass(200200L)
        );
        service1.setPassengers(
                Arrays.asList(
                        new Passenger("Harry", "Potter"),
                        new Passenger("Hermione", "Granger"),
                        new Passenger("Ron", "Weasly"),
                        new Passenger("Alistair", "Moodye"),
                        new Passenger("Sirius", "Black")
                )
        );

        service1.setPaymentMethod(new Endorsement());

        Customer customer2 = service2.findCustomerById(30L);

        service2.setCustomer(customer2);
        service2.setNumberOfSeats(5);
        service2.setTravelClass(
                service2.findTravelClass(200200L)
        );
        service2.setPassengers(
                Arrays.asList(
                        new Passenger("Albus", "Dumbledore"),
                        new Passenger("Severus", "Snape"),
                        new Passenger("Minerva", "McGonagall"),
                        new Passenger("Rubeus", "Hagrid"),
                        new Passenger("Pomona", "Sprout")
                )
        );

        service2.setPaymentMethod(new Endorsement());


        service2.doBooking();

        assertEquals(0, (long) service2.findTravelClass(200200L).getRemainingSeats());

        service1.doBooking();


    }
}
