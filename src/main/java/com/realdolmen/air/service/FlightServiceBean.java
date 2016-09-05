package com.realdolmen.air.service;

import com.realdolmen.air.domain.Flight;
import com.realdolmen.air.repository.FlightRepository;

import javax.ejb.EJB;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

@Stateless
@LocalBean
public class FlightServiceBean implements FlightService{
    @EJB
    FlightRepository flightRepository;

    @Override
    public List<Flight> findAllFlights(){
        return flightRepository.findAll();
    }

    public List<Flight> findFlightsWithParams(Long airlineCompanyId, String flightClass, int numberOfSeats, Long departureAirportId, Long arrivalAirportId, Date departureTime){
        List<Flight> correctDateFlights = new ArrayList<>();
        List<Flight> possibleFlights = flightRepository.findFlightsWithParams(airlineCompanyId,flightClass,numberOfSeats,departureAirportId,arrivalAirportId);
        if(departureTime != null){
            for(Flight flight: possibleFlights){
                if(checkSameDate(departureTime, flight.getDepartureTime())){
                    System.out.println("true");
                    correctDateFlights.add(flight);
                }
            }
            return correctDateFlights;
        }
        return possibleFlights;
    }

    public Flight findFlightById(Long id){
        return flightRepository.findById(id);
    }

    private boolean checkSameDate(Date d1, Date d2){
        Calendar calendar1 = Calendar.getInstance();
        Calendar calendar2 = Calendar.getInstance();

        calendar1.setTime(d1);
        calendar2.setTime(d2);
        return calendar1.get(Calendar.YEAR) == calendar2.get(Calendar.YEAR) && calendar1.get(Calendar.DAY_OF_YEAR) == calendar2.get(Calendar.DAY_OF_YEAR);
    }
}
