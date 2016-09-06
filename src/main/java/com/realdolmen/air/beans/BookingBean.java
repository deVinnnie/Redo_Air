package com.realdolmen.air.beans;

import com.realdolmen.air.domain.Customer;
import com.realdolmen.air.domain.Flight;
import com.realdolmen.air.domain.Passenger;
import com.realdolmen.air.domain.TravelClass;
import com.realdolmen.air.domain.payement.CreditCard;
import com.realdolmen.air.domain.payement.PaymentMethod;
import com.realdolmen.air.repository.TravelClassRepository;
import com.realdolmen.air.service.BookingService;
import com.realdolmen.air.web.controller.Phase;

import javax.annotation.PostConstruct;
import javax.enterprise.context.Conversation;
import javax.enterprise.context.ConversationScoped;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
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

    @Inject
    private Conversation conversation;

    @Inject
    private BookingService service;

    private List<Passenger> passengerList;

    @Min(1)
    private int seatsWanted;

    @Min(0)
    @NotNull
    private Long travelClassID;

    private TravelClass travelClass;

    /**
     * Convenience variable. Pulled from the TravelClass.
     */
    private Flight flight;

    private Customer customer;

    private CreditCard creditCard = new CreditCard();

    private String paymentMethod;

    /**
     * Current phase in the booking process.
     */
    private Phase phase = Phase.PASSENGER_INFORMATION;

    @PostConstruct()
    public void setUp(){
        this.seatsWanted = 3;

        passengerList = new ArrayList<>(seatsWanted);

        for(int i = 0; i < seatsWanted; i++){
            passengerList.add(new Passenger("Harry", "Potter"));
        }
    }

    public void setUpAfterParam(){
        System.out.println(travelClassID);
        //travelClassID = 200100L;
        this.travelClass = service.findTravelClass(travelClassID);
        this.flight = travelClass.getFlight();

        this.service.setNumberOfSeats(this.seatsWanted);
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

    // Phase 1 - Entering the passenger details
    //
    // Every ticket is coupled to a passenger.
    // -----------------------------------------------------------------------------------------------------------------
    public void savePassengers(){
        for(Passenger passenger : passengerList){
            System.out.println(passenger.getFirstName());
        }
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
        System.out.println(formatter.format(creditCard.getExpiryDate()));

        this.phase = Phase.CONFIRMATION;
    }

    public void saveEndorsement(){
        this.phase = Phase.CONFIRMATION;
    }

    // Phase 3: Confirmation
    // -----------------------------------------------------------------------------------------------------------------
    public String cancel(){
        return "index.xhtml?faces-redirect=true";
    }

    public String confirm(){
        try {
            service.doBooking();
            return "/redo-customer/success.xhtml?faces-redirect=true";
        }
        catch(Exception e){
            return "/redo-customer/failure.xhtml?faces-redirect=true";
        }
    }
}
