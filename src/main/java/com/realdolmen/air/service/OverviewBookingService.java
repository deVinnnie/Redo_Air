package com.realdolmen.air.service;

import com.realdolmen.air.domain.Booking;
import com.realdolmen.air.repository.BookingRepository;

import javax.ejb.EJB;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import java.util.List;

@Stateless
@LocalBean
public class OverviewBookingService {
    @EJB
    private BookingRepository bookingRepository;

    public List<Booking> findBookingsByCustomerId(Long customerId){
        return bookingRepository.findBookingsByCustomerId(customerId);
    }
}
