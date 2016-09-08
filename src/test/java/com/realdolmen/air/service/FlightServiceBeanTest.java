package com.realdolmen.air.service;

import com.realdolmen.air.domain.Airport;
import com.realdolmen.air.domain.Customer;
import com.realdolmen.air.domain.Flight;
import com.realdolmen.air.domain.TravelClass;
import com.realdolmen.air.repository.CustomerRepositoryInterface;
import com.realdolmen.air.repository.FlightRepositoryInterface;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import static org.junit.Assert.*;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class FlightServiceBeanTest {
    @Mock
    FlightRepositoryInterface flightRepositoryInterface;

    @InjectMocks
    private FlightServiceBean flightServiceBean = new FlightServiceBean();

    private Flight flight;
    private Flight flight2;
    private Flight flight3;
    private Flight flight4;
    private TravelClass travelClass;
    private TravelClass travelClass2;

    private List<Flight> flights;
    private List<Flight> pastFlights;
    private List<TravelClass> travelClasses;
    private List<TravelClass> travelClassesFromFlight;

    private Calendar calendar;

    @Before
    public void setupMox() {
        calendar = Calendar.getInstance();
        calendar.set(1994,7,18);

        Calendar calendar2 = Calendar.getInstance();
        calendar2.set(1994,7,17);

        flight = new Flight();
        flight.setId(1L);
        flight.setDepartureTime(calendar.getTime());

        flight2 = new Flight();
        flight2.setId(2L);
        flight2.setDepartureTime(calendar.getTime());

        flight3 = new Flight();
        flight3.setId(3L);
        flight3.setDepartureTime(calendar.getTime());

        flight4 = new Flight();
        flight4.setId(4L);
        flight4.setDepartureTime(calendar2.getTime());

        flights = new ArrayList<>();
        flights.add(flight);
        flights.add(flight2);
        flights.add(flight3);

        pastFlights = new ArrayList<>();
        pastFlights.add(flight4);

        travelClass = new TravelClass();
        travelClass.setId(1L);
        travelClass.setFlight(flight);
        travelClass2 = new TravelClass();
        travelClass2.setId(2L);

        travelClasses = new ArrayList<>();
        travelClasses.add(travelClass);
        travelClasses.add(travelClass2);

        travelClassesFromFlight = new ArrayList<>();
        travelClassesFromFlight.add(travelClass);



        when(flightRepositoryInterface.findAll()).thenReturn(flights);
        when(flightRepositoryInterface.findById(1L)).thenReturn(flight);
        when(flightRepositoryInterface.findTravelClasses(1L)).thenReturn(travelClassesFromFlight);
        when(flightRepositoryInterface.update(flight)).thenReturn(flight);
        when(flightRepositoryInterface.findFlightsWithParams(1L,"first",1,1L,1L)).thenReturn(flights);
        when(flightRepositoryInterface.findFlightsWithParams(2L,"first",1,1L,1L)).thenReturn(pastFlights);
        when(flightRepositoryInterface.findFlightsWithParams(2L,"first",1,1L,1L)).thenReturn(flights);
    }


    @Test
    public void test_getAllAirportsShouldReturnAll(){
        List<Flight> results = flightServiceBean.findAllFlights();

        assertNotNull(results);
        assertEquals(3, results.size());

        verify(flightRepositoryInterface).findAll();
        verifyNoMoreInteractions(flightRepositoryInterface);
    }

    @Test
    public void test_findById_ReturnsCorrectFlight(){
        Flight flightResponse = flightServiceBean.findFlightById(1L);
        assertNotNull(flightResponse);
        assertNotNull(flightResponse.getId());

        Long expected = Long.parseLong("1");
        assertEquals(expected, flightResponse.getId());

        verify(flightRepositoryInterface).findById(1L);
        verifyNoMoreInteractions(flightRepositoryInterface);
    }

    @Test
    public void test_findTravelClasses(){
        List<TravelClass> result = flightServiceBean.findTravelClasses(1L);

        assertNotNull(result);
        assertEquals(1,result.size());

        verify(flightRepositoryInterface).findTravelClasses(1L);
        verifyNoMoreInteractions(flightRepositoryInterface);

        Long expected = Long.parseLong("1");
        assertEquals(expected, result.get(0).getId());
    }

    @Test
    public void test_updateFlight(){
        Flight result = flightServiceBean.update(flight);

        assertNotNull(result);

        verify(flightRepositoryInterface).update(flight);
        verifyNoMoreInteractions(flightRepositoryInterface);

        Long expected = flight.getId();
        assertEquals(expected, result.getId());
    }

    @Test
    public void test_findFlightWithParamsWithCorrectDate(){
        List<Flight> result = flightServiceBean.findFlightsWithParams(1L,"first",1,1L,1L,calendar.getTime());

        verify(flightRepositoryInterface).findFlightsWithParams(1L,"first",1,1L,1L);
        verifyNoMoreInteractions(flightRepositoryInterface);
        assertEquals(3,result.size());
    }

    @Test
    public void test_findFlightWithParamsNoDate(){
        List<Flight> result = flightServiceBean.findFlightsWithParams(2L,"first",1,1L,1L,null);

        verify(flightRepositoryInterface).findFlightsWithParams(2L,"first",1,1L,1L);
        verifyNoMoreInteractions(flightRepositoryInterface);
        assertEquals(3,result.size());
    }
}
