package com.realdolmen.air.service;

import com.realdolmen.air.domain.Ticket;

import javax.ejb.Stateless;
import java.util.List;

@Stateless
public interface TicketService {
    List<Ticket> findTicketsByBookingId(Long bookingId);
}
