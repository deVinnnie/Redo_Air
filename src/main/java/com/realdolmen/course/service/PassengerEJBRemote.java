package com.realdolmen.course.service;

import com.realdolmen.course.domain.Passenger;
import com.realdolmen.course.domain.PassengerId;

import javax.ejb.Remote;
import java.util.List;
import java.util.concurrent.Future;

@Remote
public interface PassengerEJBRemote {
    List<Passenger> findPassengers();
    Passenger findPassengerById(PassengerId id);
    void createPassenger(Passenger passenger);
    void deletePassenger(Passenger passenger);
    void updatePassenger(Passenger passenger);
    Future<String> payByCreditCard();
}
