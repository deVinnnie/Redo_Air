package com.realdolmen.air.service;

import com.realdolmen.air.domain.Airport;
import com.realdolmen.air.domain.Passenger;
import com.realdolmen.air.repository.AirportRepository;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.List;


import static org.junit.Assert.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class AirportServiceBeanTest {

    @Mock
    private AirportRepository repository;

    @InjectMocks
    private AirportServiceBean service = new AirportServiceBean();


    private Airport airport1;
    private Airport airport2;
    private Airport airport3;

    private List<Airport> airports;

    @Before
    public void setupMox() {
        // Some made up airports, which most definitely do not exist in the real world.
        airport1 = new Airport("AIA", "Akira International Airport");
        airport1.setId(100L);
        airport2 = new Airport("MIA", "Misouri International Airport");
        airport2.setId(200L);
        airport3 = new Airport("CAM", "Canady Central Airport");
        airport3.setId(300L);

        airports = new ArrayList<>();
        airports.add(airport1);
        airports.add(airport2);
        airports.add(airport3);

        when(repository.findAllActiveAndNonActive()).thenReturn(airports);
        when(repository.findById(200L)).thenReturn(airport2);
        when(repository.findById(600L)).thenReturn(null);
        when(repository.search("aa")).thenReturn(airports);
    }

    @Test
    public void test_getAllAirportsShouldReturnAll(){
        List<Airport> results = service.getAllAirports();

        assertNotNull(results);
        assertEquals(3, results.size());

        verify(repository).findAllActiveAndNonActive();
        verifyNoMoreInteractions(repository);
    }

    @Test
    public void test_findById_ReturnsCorrectEntity(){
        Airport airport = service.findById(200L);
        assertNotNull(airport);
        assertNotNull(airport.getId());

        assertEquals("MIA", airport.getCode());
        assertEquals("Misouri International Airport", airport.getName());

        verify(repository).findById(200L);
        verifyNoMoreInteractions(repository);
    }

    @Test
    public void test_toggleAvailability_ChangesStateOnAirport() throws InvalidIdExeption {
        assertFalse(airport2.getAvailable());
        service.toggleAvailability(200L);

        assertTrue(airport2.getAvailable());

        verify(repository).findById(200L);
        verifyNoMoreInteractions(repository);
    }

    @Test
    public void test_toggleAvailability_AirportAvailableSetToTrue_ChangesStateOnAirportToFalse() throws InvalidIdExeption {
        airport2.setAvailable(true);
        assertTrue(airport2.getAvailable());

        service.toggleAvailability(200L);

        assertFalse(airport2.getAvailable());

        verify(repository).findById(200L);
        verifyNoMoreInteractions(repository);
    }

    @Test(expected = InvalidIdExeption.class)
    public void test_toggleAvailability_NonExistingAirport_ThrowsException() throws InvalidIdExeption {
        service.toggleAvailability(600L);
    }

    @Test
    public void test_findAllSearchOnlyPerformsSearch(){
        List<Airport> results = service.findAirports("aa");
        assertNotNull(results);
        assertEquals(3, results.size());

        verify(repository).search("aa");
        verifyNoMoreInteractions(repository);
    }

}
