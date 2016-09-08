package com.realdolmen.air.beans;

import com.realdolmen.air.domain.Flight;
import com.realdolmen.air.domain.TravelClass;
import com.realdolmen.air.service.FlightServiceBean;
import com.realdolmen.air.service.TravelClassServiceBean;

import javax.ejb.LocalBean;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.bean.ViewScoped;
import javax.inject.Inject;
import javax.validation.constraints.Min;
import java.io.IOException;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;

@ManagedBean
@ViewScoped
public class FlightDetailsBean implements Serializable {
    private Long flightId;

    @Min(1)
    private int numberOfSeats;

    private Flight flight;
    private List<TravelClass> travelClasses;
    private String flightClass;

    @Inject
    FlightServiceBean flightServiceBean;

    @Inject
    TravelClassServiceBean travelClassServiceBean;

    @Inject
    private RedirectionBean redirectionBean;

    public void onParametersLoaded() throws IOException {
        flight = flightServiceBean.findFlightById(flightId);

        if(flight == null){
            redirectionBean.throw404();
            return;
        }

        getAllClassesFromFlight(flightId,numberOfSeats,flightClass);
    }

    public Long getFlightId() {
        return flightId;
    }

    public void setFlightId(Long flightId) {
        this.flightId = flightId;
    }

    public int getNumberOfSeats() {
        return numberOfSeats;
    }

    public void setNumberOfSeats(int numberOfSeats) {
        this.numberOfSeats = numberOfSeats;
    }

    public Flight getFlight() {
        return flight;
    }

    public void setFlight(Flight flight) {
        this.flight = flight;
    }

    private void getAllClassesFromFlight(Long id, int numberOfSeats, String flightClass){
        travelClasses = travelClassServiceBean.findAllTravelClassesOfAFlight(id, numberOfSeats);
    }

    public List<TravelClass> getTravelClasses() {
        return travelClasses;
    }

    public void setTravelClasses(List<TravelClass> travelClasses) {
        this.travelClasses = travelClasses;
    }

    public String getFlightClass() {
        return flightClass;
    }

    public void setFlightClass(String flightClass) {
        this.flightClass = flightClass;
    }

    public String isActive(String name){
        if(name.toLowerCase().equals(flightClass.toLowerCase()))
            return "active";
        return null;
    }

    public BigDecimal totalPrice(BigDecimal base){
        return base.multiply(new BigDecimal(numberOfSeats));
    }

    public String goToBooking(Long id){
        String url = String.format("/redo-customer/booking.xhtml?travelClassID=%d&seats=%d&faces-redirect=true", id, this.numberOfSeats);
        return url;
    }
}
