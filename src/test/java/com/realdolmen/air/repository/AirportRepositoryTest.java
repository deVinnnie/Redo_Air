package com.realdolmen.air.repository;

import com.realdolmen.air.domain.Airport;
import com.realdolmen.util.persistence.JpaPersistenceTest;
import org.junit.Before;
import org.junit.Test;

import java.util.List;

public class AirportRepositoryTest extends JpaPersistenceTest{

    private AirportRepository repository;

    private static int AIRPORTS_IN_TESTDATA = 57;

    @Before
    public void setUp(){
        repository = new AirportRepository();
        repository.setEntityManager(entityManager());
    }

    //<editor-fold desc="Tests for method findById()">
    @Test
    public void test_FindById_WithExistingId_GivesCorrectAirport(){
        Airport airport = repository.findById(200L);
        assertNotNull(airport);
        assertEquals(200L, (long) airport.getId());
        assertEquals("Port Hardy", airport.getName());
    }

    @Test
    public void test_FindById_WithNonExistingId_GivesNull(){
        Airport airport = repository.findById(1L);
        assertNull(airport);
    }

    @Test
    public void test_FindById_WithNegativeId_GivesNull(){
        Airport airport = repository.findById(-1L);
        assertNull(airport);
    }

    @Test
    public void test_Search_WithNullParam_GivesEmptyList(){
        List<Airport> list = repository.search(null);
        assertNotNull(list);
    }
    //</editor-fold>

    //<editor-fold desc"Tests for findAll()">
    @Test
    public void test_FindAll_ReturnsCorrectNumberOfAirports(){
        List<Airport> airports = repository.findAll();
        assertEquals(AIRPORTS_IN_TESTDATA, airports.size());
    }
    //</editor-fold>

}