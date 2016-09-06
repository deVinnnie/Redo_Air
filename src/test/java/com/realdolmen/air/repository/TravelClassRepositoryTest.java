package com.realdolmen.air.repository;

import com.realdolmen.air.domain.Airport;
import com.realdolmen.air.domain.TravelClass;
import com.realdolmen.testutil.TestData;
import com.realdolmen.testutil.TestDataLocation;
import org.junit.Before;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

/**
 * Created by knockaert on 5/04/2016.
 */
public class TravelClassRepositoryTest extends AbstractRepositoryTest<TravelClassRepository>{

    @Test
    @TestData(dataSet = TestDataLocation.TRAVELCLASS)
    public void findShouldReturnCorrectTravelClass(){
        Long expected = Long.parseLong("1");
        TravelClass travelClass = getRepository().find(Long.parseLong("1"));
        assertEquals(expected,travelClass.getId());
    }

    @Test
    public void findShouldReturnNullWrongId(){
        TravelClass travelClass = getRepository().find(Long.parseLong("1"));
        assertEquals(null,travelClass);
    }

    @Test
    @TestData(dataSet = TestDataLocation.TRAVELCLASS)
    public void updateShouldReturnSameTravelClass(){
        TravelClass travelClass = getRepository().find(Long.parseLong("1"));
        TravelClass updated = getRepository().update(travelClass);
        assertEquals(travelClass.getId(), updated.getId());
    }

    @Test
    public void findAllTravelClassesNoDataSizeIs0(){
        List<TravelClass> travelClasses = getRepository().findAllTravelClassesOfAFlight(1L,3);
        assertEquals(0,travelClasses.size());
    }

    @Test
    @TestData(dataSet = TestDataLocation.TRAVELCLASS)
    public void findAllTravelClassesReturnSize1(){
        List<TravelClass> travelClasses = getRepository().findAllTravelClassesOfAFlight(1L,3);
        assertEquals(1,travelClasses.size());
    }
}
