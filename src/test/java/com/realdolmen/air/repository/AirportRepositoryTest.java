package com.realdolmen.air.repository;

import com.realdolmen.air.domain.AirlineCompany;
import com.realdolmen.air.domain.Airport;
import com.realdolmen.testutil.TestData;
import com.realdolmen.testutil.TestDataLocation;
import org.junit.Before;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.assertEquals;

/**
 * Created by knockaert on 5/04/2016.
 */
public class AirportRepositoryTest extends AbstractRepositoryTest<AirportRepository>{

    List<Airport> airports;

    @Before
    public void setUp(){
        airports = getRepository().findAll();
    }

    @Test
    @TestData(dataSet = TestDataLocation.AIRPORT)
    public void numberOfAirportsShouldBe5(){
        assertEquals(5,airports.size());
    }

    @Test
    public void numberOfAirportsShouldBe0(){
        assertEquals(0,airports.size());
    }

    @Test
    @TestData(dataSet = TestDataLocation.AIRPORT)
    public void findByIdShouldReturnCorrectAirport(){
        Long expected = Long.parseLong("1");
        Airport airport = getRepository().findById(Long.parseLong("1"));
        assertEquals(expected,airport.getId());
    }

    @Test
    @TestData(dataSet = TestDataLocation.AIRPORT)
    public void findByWrongId(){
        Airport airport = getRepository().findById(Long.parseLong("10"));
        assertEquals(null,airport);
    }

    @Test
    @TestData(dataSet = TestDataLocation.AIRPORT)
    public void searchReturnsCorrectNumberEmptyString(){
        List<Airport> foundAirports = getRepository().search("");
        assertEquals(5, foundAirports.size());
    }

    @Test
    @TestData(dataSet = TestDataLocation.AIRPORT)
    public void searchReturnsCorrectNumberWithString(){
        List<Airport> foundAirports = getRepository().search("air");
        assertEquals(3, foundAirports.size());
    }

    @Test
    @TestData(dataSet = TestDataLocation.AIRPORT)
    public void searchReturnsCorrectNumberWithStringMixedCase(){
        List<Airport> foundAirports = getRepository().search("ZavENTem");
        assertEquals(1, foundAirports.size());
    }

    @Test
    @TestData(dataSet = TestDataLocation.AIRPORT)
    public void searchReturnsCorrectNumberWithNonExistingString(){
        List<Airport> foundAirports = getRepository().search("Arne is de beste");
        assertEquals(0, foundAirports.size());
    }
}
