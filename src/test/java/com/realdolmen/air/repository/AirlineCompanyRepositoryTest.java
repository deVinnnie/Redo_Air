package com.realdolmen.air.repository;

import com.realdolmen.air.domain.AirlineCompany;
import com.realdolmen.testutil.TestData;
import com.realdolmen.testutil.TestDataLocation;
import org.junit.Before;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.assertEquals;

/**
 * Created by knockaert on 5/04/2016.
 */
public class AirlineCompanyRepositoryTest extends AbstractRepositoryTest<AirlineCompanyRepository>{

    List<AirlineCompany> airlineCompanies;

    @Before
    public void setUp(){
        airlineCompanies = getRepository().findAll();
    }

    @Test
    @TestData(dataSet = TestDataLocation.AIRLINECOMPANY)
    public void numberOfAirlineCompaniesShouldBe5(){
        assertEquals(5,airlineCompanies.size());
    }

    @Test
    public void numberOfAirlineCompaniesShouldBe0(){
        assertEquals(0,airlineCompanies.size());
    }

    @Test
    @TestData(dataSet = TestDataLocation.AIRLINECOMPANY)
    public void findByIdShouldReturnCorrectAirlineCompany(){
        Long expected = Long.parseLong("1");
        AirlineCompany airlineCompany = getRepository().findById(Long.parseLong("1"));
        assertEquals(expected,airlineCompany.getId());
    }

    @Test
    @TestData(dataSet = TestDataLocation.AIRLINECOMPANY)
    public void findByWrongId(){
        AirlineCompany airlineCompany = getRepository().findById(Long.parseLong("10"));
        assertEquals(null,airlineCompany);
    }
}
