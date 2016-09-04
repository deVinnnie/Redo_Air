package com.realdolmen.air.service;

import com.realdolmen.air.domain.Airport;
import com.realdolmen.air.repository.AirportRepository;
import com.realdolmen.util.integration.RemoteIntegrationTest;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

public class AirportServiceBeanIntegrationTest extends RemoteIntegrationTest {

    @Test
    public void testGetAllAirports() throws Exception {
        AirportService airportService = lookup("quickstart/AirportServiceBean!com.realdolmen.air.service.AirportService");
        List<Airport> airports = airportService.getAllAirports();
        assertEquals(57, airports.size());
        for (Airport airport : airports) {
            logger.trace("Retrieved airport " + airport);
        }
    }
}
