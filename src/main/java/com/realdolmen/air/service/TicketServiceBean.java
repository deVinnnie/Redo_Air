package com.realdolmen.air.service;

import com.realdolmen.air.domain.Booking;
import com.realdolmen.air.domain.Ticket;
import com.realdolmen.air.repository.TicketRepository;

import javax.ejb.EJB;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import java.util.Collections;
import java.util.List;

@Stateless
public class TicketServiceBean implements TicketService{
    @EJB
    TicketRepository ticketRepository;

    @Override
    public List<Ticket> findTicketsByBookingId(Long bookingId) {
        Booking booking = ticketRepository.findTicketsByBookingId(bookingId);
        if(booking == null){
            return Collections.emptyList();
        }
        return booking.getTickets();
    }

    @Override
    public Booking findBooking(Long bookingId){
        return ticketRepository.findTicketsByBookingId(bookingId);
    }
}
