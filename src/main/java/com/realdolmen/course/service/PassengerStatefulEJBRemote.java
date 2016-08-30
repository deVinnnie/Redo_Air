package com.realdolmen.course.service;

import com.realdolmen.course.domain.Address;
import com.realdolmen.course.domain.CreditCard;
import com.realdolmen.course.domain.Passenger;
import com.realdolmen.course.domain.Ticket;

import javax.ejb.Remote;

@Remote
public interface PassengerStatefulEJBRemote  {
    void createPassenger(Passenger passenger);
    void addAddress(Address address);
    void addTicket(Ticket ticket);
    void addCreditCard(CreditCard creditCard);
    void checkOut();
}
