package com.realdolmen.course.web.controller;

import com.realdolmen.course.domain.Passenger;
import com.realdolmen.course.domain.PassengerId;
import com.realdolmen.course.domain.PassengerType;
import com.realdolmen.course.repository.PassengerRepository;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.inject.Inject;
import java.io.Serializable;
import java.util.Calendar;

@ManagedBean(name = "passengerBean")
@ViewScoped
public class PassengerBean implements Serializable{

    private boolean editable = true;

    private PassengerId currentId = new PassengerId();

    private Passenger passenger;

    @Inject
    private PassengerRepository passengerRepository;

    private Calendar c;

    public void setUpAfterParam(){
        if(currentId.getSsn() != null && currentId.getLastName() != null){
            Passenger passenger = passengerRepository.findById(currentId);
            if(passenger == null){
                throw new IllegalArgumentException("Passenger does not exist");
            }

            this.passenger = passenger;
        }
        else {
            c = Calendar.getInstance();
            c.add(Calendar.YEAR, -5);

            this.passenger = new Passenger(
                    new PassengerId(
                            "123456",
                            "Potter"
                    ),
                    "Harry",
                    Calendar.getInstance().getTime(),
                    PassengerType.OCCASIONAL
            );
        }
    }

    public String register(){
        /*if(lastName.equals("") || ssn.equals("") || firstName.equals("") ||
                dateOfBirth.equals("")){
            return "register.xhtml";
        }*/
        this.editable = false;
        return null; // Use null  to stay in same view!
    }

    public String confirm() {
        /*PassengerId passengerId = new PassengerId(ssn, lastName);
        Passenger duplicate = passengerRepository.findById(passengerId);

        if(duplicate != null){
            FacesContext.getCurrentInstance().getExternalContext().getFlash().put("error", "Duplicate passenger!");

            return "main.xhtml";
        }*/

        /*Passenger passenger = new Passenger(
                passengerId,
                firstName,
                c.getTime(),
                type
        );*/

        passengerRepository.save(this.passenger);

        return "all-passengers.xhtml?faces-redirect=true";
    }

    public String update(){
        passengerRepository.update(passenger);
        return "all-passengers.xhtml?faces-redirect=true";
    }

    public String review(){
        this.editable = true;
        return null;
    }

    public boolean isEditable() {
        return editable;
    }

    public void setEditable(boolean editable) {
        this.editable = editable;
    }

    public Passenger getPassenger() {
        return passenger;
    }

    public void setPassenger(Passenger passenger) {
        this.passenger = passenger;
    }

    public PassengerId getCurrentId() {
        return currentId;
    }

    public void setCurrentId(PassengerId currentId) {
        this.currentId = currentId;
    }

    public String delete(PassengerId passengerId) {
        this.passengerRepository.remove(passengerId);
        return "all-passengers.xhtml?faces-redirect=true";
    }
}
