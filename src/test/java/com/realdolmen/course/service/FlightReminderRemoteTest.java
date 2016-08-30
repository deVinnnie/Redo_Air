package com.realdolmen.course.service;

import com.realdolmen.course.domain.DomesticFlight;
import com.realdolmen.course.domain.Flight;
import com.realdolmen.course.domain.Passenger;
import com.realdolmen.course.domain.Ticket;
import com.realdolmen.course.utilities.integration.RemoteIntegrationTest;
import org.junit.Test;

import java.util.Calendar;
import java.util.List;
import java.util.concurrent.Future;


public class FlightReminderRemoteTest extends RemoteIntegrationTest {
    @Test
    public void testPersonServiceCanBeAccessedRemotely() throws Exception {
        FlightReminderEJBRemote flightEjb = lookup("jee7-starter/FlightReminderEJB!com.realdolmen.course.service.FlightReminderEJBRemote");

        Calendar c = Calendar.getInstance();
        c.add(Calendar.MINUTE, 2);

        Ticket ticket = new Ticket();
        ticket.setOutFlight(
                new DomesticFlight("123", c.getTime(), c.getTime())
        );

        flightEjb.createTicket(
            ticket
        );

    }
}
