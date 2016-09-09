package com.realdolmen.air.service;

import com.realdolmen.air.domain.*;
import com.realdolmen.air.domain.payment.PaymentMethod;

import javax.ejb.Remote;
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
