package com.realdolmen.air.repository;

import com.realdolmen.air.domain.AirlineCompany;
import com.realdolmen.air.domain.Booking;
import com.realdolmen.air.domain.Ticket;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.ejb.Stateless;
import javax.persistence.NoResultException;
import java.util.Collections;
import java.util.List;

@Stateless
public class TicketRepository extends AbstractBaseRepository<Ticket, Long>  {
    private static final Logger LOGGER = LoggerFactory.getLogger(FlightRepository.class);
    private static String info = "No information found: ";

    public Booking findTicketsByBookingId(Long bookingId) {
        try {
            return getEntityManager().find(Booking.class, bookingId);
        } catch (NoResultException e) {
            LOGGER.error(info, e);
        }
        return null;
    }
}
