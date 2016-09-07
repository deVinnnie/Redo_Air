package com.realdolmen.air.repository;

import com.realdolmen.air.domain.Booking;
import com.realdolmen.air.domain.Customer;
import com.realdolmen.air.domain.Ticket;
import com.realdolmen.air.domain.TravelClass;

import javax.ejb.Stateless;
import javax.persistence.Entity;
import javax.persistence.EntityManager;
import javax.persistence.LockModeType;
import javax.persistence.PersistenceContext;

@Stateless
public class BookingRepository {

    @PersistenceContext
    EntityManager em;


    public TravelClass findTravelClass(Long id) {
        TravelClass travelClass = em.find(TravelClass.class, id);/*, LockModeType.OPTIMISTIC_FORCE_INCREMENT);*/
        return travelClass;
    }

    public TravelClass updateTravelClass(TravelClass travelClass){
        travelClass = em.merge(travelClass);
        return travelClass;
    }

    public void create(Booking booking) {
        booking.setCustomer(em.find(Customer.class, booking.getCustomer().getId()));
        em.persist(booking.getPayment().getMethod());
        em.persist(booking.getPayment());

        for(Ticket ticket : booking.getTickets()){
            em.persist(ticket);
        }

        em.persist(booking);
    }
}
