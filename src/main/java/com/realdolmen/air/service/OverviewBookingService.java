package com.realdolmen.air.service;

import com.realdolmen.air.domain.*;
import com.realdolmen.air.domain.payement.CreditCard;
import com.realdolmen.air.domain.payement.Payment;
import com.realdolmen.air.domain.payement.PaymentMethod;
import com.realdolmen.air.domain.payement.PaymentStatus;
import com.realdolmen.air.repository.BookingRepository;
import com.realdolmen.air.repository.TravelClassRepositoryInterface;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.ejb.LocalBean;
import javax.ejb.Stateful;
import javax.ejb.Stateless;
import javax.inject.Inject;
import java.math.BigDecimal;
import java.util.ArrayList;
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
