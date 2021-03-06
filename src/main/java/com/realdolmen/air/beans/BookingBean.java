package com.realdolmen.air.beans;


import com.realdolmen.air.domain.Booking;
import com.realdolmen.air.domain.Flight;
import com.realdolmen.air.domain.Passenger;
import com.realdolmen.air.domain.TravelClass;
import com.realdolmen.air.domain.payment.CreditCard;
import com.realdolmen.air.domain.payment.Endorsement;
import com.realdolmen.air.service.BookingServiceBean;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.annotation.PostConstruct;
import javax.ejb.EJBTransactionRolledbackException;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.inject.Inject;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

/**
 * Notes:
 *
 * - The flight entity does not need to be passed.
 *   TravelClass has a reference to flight!
 */
@ManagedBean
@ViewScoped
public class BookingBean implements Serializable{

    private Logger logger = LoggerFactory.getLogger(BookingBean.class);

    @Inject
    private BookingServiceBean service;

    @Inject
    private UserBean userBean;

    @Inject
    private RedirectionBean redirectionBean;

    private List<Passenger> passengerList;

    @Min(1)
    private int seatsWanted = 1;

    private Long travelClassID;

    private TravelClass travelClass;

    /**
     * Convenience variable. Pulled from the TravelClass.
     */
    private Flight flight;

    private CreditCard creditCard = new CreditCard();

    private String paymentMethod;

    /**
     * Current phase in the booking process.
     */
    private Phase phase = Phase.PASSENGER_INFORMATION;

    @PostConstruct()
    public void setUp(){
        this.service.setCustomer(userBean.getUserAsCustomer());
    }

    public void setUpAfterParam(){
        if(travelClassID == null){
            redirectionBean.throw404();
            return;
        }

        this.travelClass = service.findTravelClass(travelClassID);
        if(travelClass == null){
            redirectionBean.throw404();
            return;
        }

        this.service.setTravelClass(travelClass);

        this.flight = travelClass.getFlight();

        this.service.setNumberOfSeats(this.seatsWanted);
        this.passengerList = new ArrayList<>(seatsWanted);

        for(int i = 0; i < seatsWanted; i++){
            passengerList.add(new Passenger());
        }
    }

    //<editor-fold desc="Getters & Setters">
    public List<Passenger> getPassengerList() {
        return passengerList;
    }

    public void setPassengerList(List<Passenger> passengerList) {
        this.passengerList = passengerList;
    }

    public int getSeatsWanted() {
        return seatsWanted;
    }

    public void setSeatsWanted(int seatsWanted) {
        this.seatsWanted = seatsWanted;
    }

    public CreditCard getCreditCard() {
        return creditCard;
    }

    public void setCreditCard(CreditCard creditCard) {
        this.creditCard = creditCard;
    }

    public Long getTravelClassID() {
        return travelClassID;
    }

    public void setTravelClassID(Long travelClassID) {
        this.travelClassID = travelClassID;
    }

    public Flight getFlight() {
        return flight;
    }

    public void setFlight(Flight flight) {
        this.flight = flight;
    }

    public TravelClass getTravelClass() {
        return travelClass;
    }

    public void setTravelClass(TravelClass travelClass) {
        this.travelClass = travelClass;
    }

    public String getPaymentMethod() {
        return paymentMethod;
    }

    public void setPaymentMethod(String paymentMethod) {
        this.paymentMethod = paymentMethod;
    }

    public Phase getPhase() {
        return phase;
    }
    //</editor-fold>

    public Booking getBooking(){
        return this.service.getBooking();
    }

    // Phase 1 - Entering the passenger details
    //
    // Every ticket is coupled to a passenger.
    // -----------------------------------------------------------------------------------------------------------------
    public void savePassengers(){
        service.setPassengers(passengerList);
        this.phase = Phase.PAYMENT_METHOD;
    }

    // Phase 2 - Choosing the payment method
    //
    // The customer can choose from a variety of method to
    // pay his tickets. These do not however include doing the dishes.
    // -----------------------------------------------------------------------------------------------------------------
    public void selectCreditCardPayment(){
        this.paymentMethod = "payment.credit-card";

        // Ask credit card info
        this.phase = Phase.PAYMENT_INFORMATION;
    }

    public void selectEndorsementPayment(){
        this.paymentMethod = "payment.endorsement";

        // Go Directly to Confirmation.
        this.phase = Phase.PAYMENT_INFORMATION;
    }

    // Phase 3 - Payment Information (Credit Card Info)
    //
    // If the customer wants to pay with credit card,
    // then the credit card details will be asked here.
    // For endorsements: the customer can navigate to the confirmation page.
    // -----------------------------------------------------------------------------------------------------------------
    public void saveCreditCard(){
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");

        this.service.setPaymentMethod(this.creditCard);
        this.phase = Phase.CONFIRMATION;
    }

    public void saveEndorsement(){
        service.setPaymentMethod(new Endorsement());
        this.phase = Phase.CONFIRMATION;
    }

    // Phase 3: Confirmation
    // -----------------------------------------------------------------------------------------------------------------
    public String cancel(){
        return "site-index?faces-redirect=true";
    }

    public String confirm(){
        try {
            service.doBooking();
            return "/redo-customer/success.xhtml?faces-redirect=true";
        }
        catch(EJBTransactionRolledbackException e){
            logger.info("--------- Booking failed. ------------");
            logger.info(e.getMessage());
            return "/redo-customer/failure.xhtml?faces-redirect=true";
        }
        catch(Exception e){
            return "/redo-customer/failure.xhtml?faces-redirect=true";
        }
    }
}
