package com.realdolmen.air.service;

import com.realdolmen.air.domain.AirlineCompany;
import com.realdolmen.air.repository.AirlineCompanyRepositoryInterface;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import static org.junit.Assert.*;
import static org.mockito.Mockito.*;


import java.util.ArrayList;
import java.util.List;

@RunWith(MockitoJUnitRunner.class)
public class AirlineCompanyServiceBeanTest {
    @Mock
    AirlineCompanyRepositoryInterface airlineCompanyRepositoryInterface;

    @InjectMocks
    private AirlineCompanyServiceBean airlineCompanyServiceBean = new AirlineCompanyServiceBean();

    private AirlineCompany airlineCompany;
    private AirlineCompany airlineCompany2;
    private AirlineCompany airlineCompany3;

    private List<AirlineCompany> airlineCompanies;

    @Before
    public void setupMox() {
        airlineCompany = new AirlineCompany();
        airlineCompany.setId(1L);
        airlineCompany2 = new AirlineCompany();
        airlineCompany2.setId(2L);
        airlineCompany3 = new AirlineCompany();
        airlineCompany3.setId(3L);

        airlineCompanies = new ArrayList<>();
        airlineCompanies.add(airlineCompany);
        airlineCompanies.add(airlineCompany2);
        airlineCompanies.add(airlineCompany3);

        when(airlineCompanyRepositoryInterface.findAll()).thenReturn(airlineCompanies);
        when(airlineCompanyRepositoryInterface.findById(1L)).thenReturn(airlineCompany);
        when(airlineCompanyRepositoryInterface.findById(600L)).thenReturn(null);
        when(airlineCompanyRepositoryInterface.findAllActive()).thenReturn(airlineCompanies);
        when(airlineCompanyRepositoryInterface.findAirlineCompaniesSearch("aa")).thenReturn(airlineCompanies);
    }


    @Test
    public void test_getAllAirlineCompaniesShouldReturnAll(){
        List<AirlineCompany> results = airlineCompanyServiceBean.getAllAirlineCompanies();

        assertNotNull(results);
        assertEquals(3, results.size());

        verify(airlineCompanyRepositoryInterface).findAll();
        verifyNoMoreInteractions(airlineCompanyRepositoryInterface);
    }

    @Test
    public void test_findByIdReturnsCorrectEntity(){
        AirlineCompany airlineCompany = airlineCompanyServiceBean.findById(1L);
        assertNotNull(airlineCompany);
        assertNotNull(airlineCompany.getId());

        Long expected = 1L;
        assertEquals(expected, airlineCompany.getId());

        verify(airlineCompanyRepositoryInterface).findById(1L);
        verifyNoMoreInteractions(airlineCompanyRepositoryInterface);
    }

    @Test
    public void test_toggleAvailability_ChangesStateOnAirlineCompany() throws InvalidIdExeption {
        airlineCompany.setAvailable(false);
        airlineCompany2.setAvailable(true);
        assertFalse(airlineCompany.getAvailable());
        airlineCompanyServiceBean.toggleAvailability(1L);

        assertTrue(airlineCompany2.getAvailable());

        verify(airlineCompanyRepositoryInterface).findById(1L);
        verifyNoMoreInteractions(airlineCompanyRepositoryInterface);
    }

    @Test(expected = InvalidIdExeption.class)
    public void test_toggleAvailability_NonExistingAirlineCompany_ThrowsException() throws InvalidIdExeption {
        airlineCompanyServiceBean.toggleAvailability(600L);
    }

    @Test
    public void test_findAllActiveOnlyPerformsFindAll(){
        List<AirlineCompany> results = airlineCompanyServiceBean.findAllActive();
        assertNotNull(results);
        assertEquals(3, results.size());

        verify(airlineCompanyRepositoryInterface).findAllActive();
        verifyNoMoreInteractions(airlineCompanyRepositoryInterface);
    }

    @Test
    public void test_findAllSearchOnlyPerformsSearch(){
        List<AirlineCompany> results = airlineCompanyServiceBean.findAirlineCompaniesSearch("aa");
        assertNotNull(results);
        assertEquals(3, results.size());

        verify(airlineCompanyRepositoryInterface).findAirlineCompaniesSearch("aa");
        verifyNoMoreInteractions(airlineCompanyRepositoryInterface);
    }
}
