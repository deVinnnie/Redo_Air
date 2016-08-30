package com.realdolmen.course.service;

import com.realdolmen.course.domain.Passenger;
import com.realdolmen.course.utilities.integration.RemoteIntegrationTest;
import org.junit.Test;

import javax.naming.NamingException;
import java.util.List;
import java.util.concurrent.Future;


public class PassengerEJBRemoteTest extends RemoteIntegrationTest {
    @Test
    public void testPersonServiceCanBeAccessedRemotely() throws Exception {
        PassengerEJBRemote passengerEJB = lookup("jee7-starter/PassengerEJB!com.realdolmen.course.service.PassengerEJBRemote");
        List<Passenger> passengers = passengerEJB.findPassengers();
        assertEquals(3, passengers.size());
        logger.trace("---------------*---------------*");
        for (Passenger passenger : passengers) {
            logger.trace("Retrieved person " + passenger);
        }
        logger.trace("---------------*---------------*");
    }

    @Test
    public void testPaymenentByCreditCard() throws Exception {
        PassengerEJBRemote passengerEJB = lookup("jee7-starter/PassengerEJB!com.realdolmen.course.service.PassengerEJBRemote");
        Future<String> result = passengerEJB.payByCreditCard();
        while(!result.isDone()) {
            System.out.println("Busy");
            Thread.sleep(500);
        }

        System.out.println(result.get());
    }
}
