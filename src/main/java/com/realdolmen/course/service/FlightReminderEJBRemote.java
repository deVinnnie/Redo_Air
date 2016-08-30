package com.realdolmen.course.service;

import com.realdolmen.course.domain.Ticket;
import com.realdolmen.course.repository.TicketRepository;

import javax.annotation.Resource;
import javax.ejb.*;
import javax.inject.Inject;
import java.util.Calendar;
import java.util.GregorianCalendar;

@Remote
public interface FlightReminderEJBRemote {

    /**
     * Create a new Ticket and shedule a reminder to be shown 30 seconds before the flight leaves.
     *
     * @param ticket
     */
    void createTicket(Ticket ticket);

}
