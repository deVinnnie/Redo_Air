package com.realdolmen.air.repository;

import com.realdolmen.air.domain.Airport;
import com.realdolmen.air.domain.Flight;
import com.realdolmen.air.domain.TravelClass;
import com.realdolmen.testutil.TestData;
import com.realdolmen.testutil.TestDataLocation;
import org.junit.Before;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

/**
 * Created by knockaert on 5/04/2016.
 */
public class FlightRepositoryTest extends AbstractRepositoryTest<FlightRepository>{

    List<Flight> flights;

    @Before
    public void setUp(){
        flights = getRepository().findAll();
    }

    @Test
    @TestData(dataSet = TestDataLocation.FLIGHT)
    public void findByIdShouldReturnCorrectFlight(){
        Flight flight = getRepository().findById(Long.parseLong("1"));
        Long expected = Long.parseLong("1");
        assertEquals(expected, flight.getId());
    }

    @Test
    public void findByIdShouldNull(){
        Flight flight = getRepository().findById(Long.parseLong("10"));
        assertEquals(null,flight);
    }

    @Test
    @TestData(dataSet = TestDataLocation.FLIGHT)
    public void numberOfTravelClassesShouldBe3(){
        List<TravelClass> travelClasses = getRepository().findTravelClasses(1L);
        assertEquals(3,travelClasses.size());
    }

    @Test
    public void numberOfTravelClassesShouldBe0(){
        List<TravelClass> travelClasses = getRepository().findTravelClasses(1L);
        assertEquals(0,travelClasses.size());
    }

    @Test
    @TestData(dataSet = TestDataLocation.FLIGHT)
    public void updateShouldReturnFlight(){
        Flight flight = getRepository().findById(Long.parseLong("1"));
        Flight updated = getRepository().update(flight);
        assertEquals(flight.getId(), updated.getId());
    }

    @Test
    @TestData(dataSet = TestDataLocation.FLIGHT)
    public void numberOfFlightsShouldBe5(){
        assertEquals(5,flights.size());
    }

    @Test
    public void numberOfFlightsShouldBe0(){
        assertEquals(0,flights.size());
    }

    @Test
    @TestData(dataSet = TestDataLocation.FLIGHT)
    public void getFlightsWithParamsShouldReturn1(){
        List<Flight> flights = getRepository().findFlightsWithParams(1L,"first",3,1L,2L);
        assertEquals(1,flights.size());
    }

    @Test
    @TestData(dataSet = TestDataLocation.FLIGHT)
    public void getFlightsWithParamsShouldReturn0Seats(){
        List<Flight> flights = getRepository().findFlightsWithParams(1L,"first",30,1L,2L);
        assertEquals(0,flights.size());
    }

    @Test
    @TestData(dataSet = TestDataLocation.FLIGHT)
    public void getFlightsWithParamsShouldReturn0Departure(){
        List<Flight> flights = getRepository().findFlightsWithParams(1L,"first",3,10L,2L);
        assertEquals(0,flights.size());
    }

    @Test
    @TestData(dataSet = TestDataLocation.FLIGHT)
    public void getFlightsWithParamsShouldReturn0Arival(){
        List<Flight> flights = getRepository().findFlightsWithParams(1L,"first",3,1L,20L);
        assertEquals(0,flights.size());
    }

    @Test
    @TestData(dataSet = TestDataLocation.FLIGHT)
    public void getFlightsWithParamsShouldReturn0Class(){
        List<Flight> flights = getRepository().findFlightsWithParams(1L,"firstC",3,1L,20L);
        assertEquals(0,flights.size());
    }
}
