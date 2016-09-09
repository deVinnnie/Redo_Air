package com.realdolmen.air.repository;

import com.realdolmen.air.domain.Booking;
import com.realdolmen.air.domain.Customer;
import com.realdolmen.air.domain.Ticket;
import com.realdolmen.air.domain.TravelClass;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.ejb.Stateless;
import javax.persistence.NoResultException;
import javax.transaction.Transactional;
import java.util.Collections;
import java.util.List;

@Stateless
@Transactional
public class BookingRepository extends AbstractBaseRepository<Booking, Long> {
    private static final Logger LOGGER = LoggerFactory.getLogger(FlightRepository.class);

    public TravelClass findTravelClass(Long id) {
        return getEntityManager().find(TravelClass.class, id);/*, LockModeType.OPTIMISTIC_FORCE_INCREMENT);*/
    }

    public TravelClass updateTravelClass(TravelClass travelClass) {
        return getEntityManager().merge(travelClass);
    }

    public void create(Booking booking) {
        booking.setCustomer(getEntityManager().find(Customer.class, booking.getCustomer().getId()));
        getEntityManager().persist(booking.getPayment().getMethod());
        getEntityManager().persist(booking.getPayment());

        for (Ticket ticket : booking.getTickets()) {
            getEntityManager().persist(ticket);
        }

        getEntityManager().persist(booking);
    }

    public List<Booking> findBookingsByCustomerId(Long customerId) {
        try {
            return getEntityManager().createNamedQuery(Booking.FIND_ALL_BY_CUSTOMERID, Booking.class).setParameter("id",customerId).getResultList();
        } catch (NoResultException e) {
            LOGGER.error("No information found: ", e);
        }
        return Collections.emptyList();
    }
}
