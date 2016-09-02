package com.realdolmen.air.beans;

import com.realdolmen.air.domain.AirlineCompany;
import com.realdolmen.air.domain.Airport;
import com.realdolmen.air.domain.Flight;
import com.realdolmen.air.service.*;
import org.primefaces.event.SelectEvent;

import javax.annotation.PostConstruct;
import javax.ejb.LocalBean;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

@LocalBean
@ManagedBean
public class searchFlightBean implements Serializable {
    private Flight flight;
    private String flightClass;
    private List<AirlineCompany> airlineCompanies;
    private List<Airport> airports;
    private Date currentDate;
    private int number3 = 1;
    private List<Flight> flights;
    private AirlineCompany airlineCompany;
    private Long airlineCompanyId;

    private Long departureAirportId;
    private Long arrivalAirportId;

    private Date departureTime;

    @Inject
    private AirlineCompanyServiceBean airlineCompanyServiceBean;

    @Inject
    private AirportServiceBean airportServiceBean;

    @Inject
    private FlightServiceBean flightServiceBean;

    public searchFlightBean(){
    }

    @PostConstruct
    public void postConstruct(){
        airlineCompanies = airlineCompanyServiceBean.getAllAirlineCompanies();
        airports = airportServiceBean.getAllAirports();
        flight = new Flight();
        currentDate = Calendar.getInstance().getTime();
        flights = flightServiceBean.findAllFlights();
    }

    public Flight getFlight() {
        return flight;
    }

    public void setFlight(Flight flight) {
        this.flight = flight;
    }

    public String getFlightClass() {
        return flightClass;
    }

    public void setFlightClass(String flightClass) {
        this.flightClass = flightClass;
    }

    public List<AirlineCompany> getAirlineCompanies() {
        return airlineCompanies;
    }

    public void setAirlineCompanies(List<AirlineCompany> airlineCompanies) {
        this.airlineCompanies = airlineCompanies;
    }

    public List<Airport> getAirports() {
        return airports;
    }

    public void setAirports(List<Airport> airports) {
        this.airports = airports;
    }


    public void onDateSelect(SelectEvent event) {
        FacesContext facesContext = FacesContext.getCurrentInstance();
        SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
        facesContext.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Date Selected", format.format(event.getObject())));
    }

    public Date getCurrentDate() {
        return currentDate;
    }

    public void setCurrentDate(Date currentDate) {
        this.currentDate = currentDate;
    }

    public int getNumber3() {
        return number3;
    }

    public void setNumber3(int number3) {
        this.number3 = number3;
    }

    public List<Flight> getFlights() {
        return flights;
    }

    public void setFlights(List<Flight> flights) {
        this.flights = flights;
    }



    public AirlineCompany getAirlineCompany() {
        return airlineCompany;
    }

    public void setAirlineCompany(AirlineCompany airlineCompany) {
        this.airlineCompany = airlineCompany;
    }

    public Long getAirlineCompanyId() {
        return airlineCompanyId;
    }

    public void setAirlineCompanyId(Long airlineCompanyId) {
        this.airlineCompanyId = airlineCompanyId;
    }

    public String getById(Long id){
        AirlineCompany airlineCompany = airlineCompanyServiceBean.findById(id);
        return airlineCompany.getName();
    }

    public String findCompanyById(Long id){
        return airlineCompanyServiceBean.findById(id).getName();
    }

    public Airport findAirportById(Long id){
        return airportServiceBean.findById(id);
    }

    public Long getDepartureAirportId() {
        return departureAirportId;
    }

    public void setDepartureAirportId(Long departureAirportId) {
        this.departureAirportId = departureAirportId;
    }

    public Long getArrivalAirportId() {
        return arrivalAirportId;
    }

    public void setArrivalAirportId(Long arrivalAirportId) {
        this.arrivalAirportId = arrivalAirportId;
    }

    public Date getDepartureTime() {
        return departureTime;
    }

    public void setDepartureTime(Date departureTime) {
        this.departureTime = departureTime;
    }

    public void searchFlights(){
        flights = flightServiceBean.findFlightsWithParams(airlineCompanyId,flightClass,number3,departureAirportId,arrivalAirportId,departureTime);
//        for (AirlineCompany ac: getAirlineCompanies()) {
//            if (ac.getId() == flight.getAirlineCompany().getId()) {
//
//            }
//        }
        System.out.println(flights.size());
        System.out.println("lel");
    }
}
