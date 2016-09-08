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
import javax.ejb.Stateful;
import javax.inject.Inject;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Stateful
public class BookingService {

    @Inject
    private PaymentService paymentService;

    @EJB
    private TravelClassRepositoryInterface travelClassRepository;

    @Inject
    private BookingRepository bookingRepository;

    private int numberOfSeats;

    private TravelClass travelClass;

    private List<Passenger> passengers;

    private PaymentMethod paymentMethod;

    private Booking booking;

    public TravelClass findTravelClass(Long id){
        return this.travelClassRepository.find(id);
    }

    @PostConstruct
    public void setUp(){
        this.booking = new Booking();
    }

    // Setters
    // ----------------------------------------------------------------------------------------
    public void setTravelClass(TravelClass travelClass){
        this.travelClass = travelClass;
    }

    public void setPassengers(List<Passenger> passengers){
        this.passengers = passengers;
    }

    public void setNumberOfSeats(int seats){
        this.numberOfSeats = seats;
    }

    public void setCustomer(Customer customer){
        this.booking.setCustomer(customer);
    }

    public void setPaymentMethod(PaymentMethod paymentMethod){
        this.paymentMethod = paymentMethod;

        if(paymentMethod instanceof CreditCard){
            booking.setDiscount(
                    new Discount( new BigDecimal("0.10"))
            );
        }

        this.makeTickets();
    }

    private void makeTickets(){
        List<Ticket> tickets = new ArrayList<>(passengers.size());

        for(Passenger passenger : passengers){
            Ticket ticket = new Ticket(passenger, travelClass);
            tickets.add(ticket);
        }

        booking.setTickets(tickets);
    }

    public Booking getBooking() {
        return booking;
    }

    // Real methods
    // ----------------------------------------------------------------------------------------

    private boolean checkPrerequisites(){
        return(
                numberOfSeats > 0
                && passengers != null
                && travelClass != null
        );
    }

    public void doBooking(){
        bookingRepository.findTravelClass(this.travelClass.getId());

        if(travelClass.getRemainingSeats() >= this.numberOfSeats){

            // OK.
            this.makeTickets();

            Payment payment = new Payment();
            payment.setMethod(this.paymentMethod);

            // Credit Card
            if(paymentMethod instanceof CreditCard) {
                paymentService.doPayment(null, null);
                payment.setStatus(PaymentStatus.SUCCEEDED);
            }
            else{
                payment.setStatus(PaymentStatus.PENDING);
            }
            booking.setPayment(payment);


            // Update number of remaining seats.
            travelClass.setRemainingSeats(
                    travelClass.getRemainingSeats()-booking.getTickets().size()
            );

            bookingRepository.updateTravelClass(travelClass);
            bookingRepository.create(booking);

            /*try {
                 // Commit
            }
            catch(OptimisticLockException exception){
                exception.printStackTrace();
            }*/
        }
    }
}
