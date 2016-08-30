package com.realdolmen.course.service;

import com.realdolmen.course.domain.*;
import com.realdolmen.course.utilities.integration.RemoteIntegrationTest;
import org.junit.Test;

import java.util.Date;

public class PassengerStatefulEJBRemoteTest extends RemoteIntegrationTest{

        @Test
        public void testPassengerServiceCanBeAccessedRemotely() throws Exception {
            PassengerStatefulEJBRemote ejb = lookup("jee7-starter/PassengerStatefulEJB!com.realdolmen.course.service.PassengerStatefulEJBRemote");

            ejb.createPassenger(new Passenger(
                    new PassengerId("123", "Potter"),
                    "Harry",
                    new Date(),
                    PassengerType.OCCASIONAL
            ));

            ejb.addAddress(new Address(
                    "Privet Drive",
                    "4",
                    "Little Whinging",
                    "1490",
                    "United Kingdom"
            ));

            ejb.addCreditCard(
                    new CreditCard(
                        "1234", "2015-06-01", 125, CreditCardType.VISA
                    )
            );

            ejb.addTicket(new Ticket(47.5));

            ejb.checkOut();


        }
}
