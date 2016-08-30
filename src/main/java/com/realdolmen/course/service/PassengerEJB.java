package com.realdolmen.course.service;

import com.realdolmen.course.domain.Passenger;
import com.realdolmen.course.domain.PassengerId;
import com.realdolmen.course.repository.PassengerRepository;

import javax.ejb.AsyncResult;
import javax.ejb.Asynchronous;
import javax.ejb.Stateless;
import javax.inject.Inject;
import java.util.List;
import java.util.concurrent.Future;

@Stateless
public class PassengerEJB implements PassengerEJBRemote{

    @Inject
    private PassengerRepository passengerRepository;


    public List<Passenger> findPassengers(){
        return passengerRepository.findAll();
    }

    public Passenger findPassengerById(PassengerId id){
        return passengerRepository.findById(id);
    }

    public void createPassenger(Passenger passenger){
        passengerRepository.save(passenger);
    }

    public void deletePassenger(Passenger passenger){
        passengerRepository.remove(passenger);
    }

    public void updatePassenger(Passenger passenger){
        passengerRepository.update(passenger);
    }

    @Asynchronous
    public Future<String> payByCreditCard(){
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        return new AsyncResult<>("Payment Succesful");
    }
}
