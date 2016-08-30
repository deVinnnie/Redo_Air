package com.realdolmen.course.service;

import com.realdolmen.course.domain.Ticket;
import com.realdolmen.course.repository.TicketRepository;

import javax.annotation.Resource;
import javax.ejb.*;
import javax.inject.Inject;
import javax.persistence.Entity;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.Calendar;
import java.util.GregorianCalendar;

@Stateless
public class FlightReminderEJB implements FlightReminderEJBRemote{

    @Resource
    TimerService timerService;

    @Inject
    private TicketRepository ticketRepository;

    @PersistenceContext
    EntityManager em;

    /**
     * Create a new Ticket and shedule a reminder to be shown 30 seconds before the flight leaves.
     *
     * @param ticket
     */
    public void createTicket(Ticket ticket) {
        em.persist(ticket.getOutFlight());
        ticketRepository.save(ticket);


        Calendar departureTime = new GregorianCalendar();
        departureTime.setTime(ticket.getOutFlight().getDepartureTime());
        departureTime.add(Calendar.SECOND, -30);

        System.out.println(departureTime);


        ScheduleExpression reminder = new ScheduleExpression().
                dayOfMonth(departureTime.get(Calendar.DAY_OF_MONTH)).
                year(departureTime.get(Calendar.YEAR)).
                month(departureTime.get(Calendar.MONTH+1)).
                hour(departureTime.get(Calendar.HOUR)).
                minute(departureTime.get(Calendar.MINUTE)).
                second(departureTime.get(Calendar.SECOND));


        timerService.createCalendarTimer(reminder,
                new TimerConfig(ticket, false)); // false for persistent

        System.out.println("--> Sheduled!");
    }

    @Timeout
    public void sendReminder(Timer timer) {
        Ticket ticket = (Ticket) timer.getInfo();
        System.out.println("Hey! It's time to go!");
    }

}
