package com.realdolmen.air.service;

import com.realdolmen.air.domain.Booking;
import com.realdolmen.air.domain.Passenger;
import com.realdolmen.air.domain.Ticket;
import com.realdolmen.air.domain.TravelClass;
import com.realdolmen.air.domain.payement.Payment;
import com.realdolmen.air.domain.payement.PaymentMethod;
import com.realdolmen.air.domain.payement.PaymentStatus;
import com.realdolmen.air.repository.TravelClassRepository;

import javax.ejb.Stateful;
import javax.ejb.Stateless;
import javax.enterprise.context.ConversationScoped;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.ArrayList;
import java.util.List;

@Stateful
public class BookingService {

    @Inject
    private PaymentService paymentService;

    @PersistenceContext
    private EntityManager entityManager;

    @Inject
    private TravelClassRepository travelClassRepository;

    private int numberOfSeats;

    private TravelClass travelClass;

    private List<Passenger> passengers;

    private PaymentMethod paymentMethod;

    private Booking booking;

    public TravelClass findTravelClass(Long id){
        return this.travelClassRepository.find(id);
    }

    public void setPassengers(List<Passenger> passengers){
        this.passengers = passengers;
    }

    public void setNumberOfSeats(int seats){
        this.numberOfSeats = seats;
    }

    public void setPaymentMethod(PaymentMethod paymentMethod){
        this.paymentMethod = paymentMethod;
    }

    private boolean checkPrerequisites(){
        return(
                numberOfSeats > 0
                && passengers != null
                && travelClass != null
                );
    }

    public void doBooking(){
        if(travelClass.getRemainingSeats() >= this.numberOfSeats){

            // OK.



            TravelClass travelClass = new TravelClass();

            List<Passenger> passengers = new ArrayList<>();

            List<Ticket> tickets = new ArrayList<>(passengers.size());

            // Make tickets
            for(Passenger passenger : passengers){
                tickets.add(
                    new Ticket(passenger, travelClass)
                );
            }

            Payment payment = new Payment();

            // Credit Card
            paymentService.doPayment(null, null);
            payment.setStatus(PaymentStatus.SUCCEEDED);

            // Update number of remaining seats.
            travelClass.setRemainingSeats(
                    travelClass.getRemainingSeats()-tickets.size()
            );
        }
    }
}
