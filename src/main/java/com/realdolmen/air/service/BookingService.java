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
import javax.ejb.Remote;
import javax.ejb.Remove;
import javax.ejb.Stateful;
import javax.inject.Inject;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Remote
public interface BookingService {

    TravelClass findTravelClass(Long id);

    // Setters
    // ----------------------------------------------------------------------------------------
    void setTravelClass(TravelClass travelClass);
    void setPassengers(List<Passenger> passengers);
    void setNumberOfSeats(int seats);
    int getNumberOfSeats();
    void setCustomer(Customer customer);
    void setPaymentMethod(PaymentMethod paymentMethod);

    Customer findCustomerById(Long id);

    Booking getBooking();

    // Real methods
    // ----------------------------------------------------------------------------------------

    void doBooking();
}
