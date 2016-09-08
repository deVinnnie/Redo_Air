package com.realdolmen.air.domain;

import org.junit.Test;

import java.time.Duration;
import java.time.temporal.ChronoUnit;
import java.time.temporal.Temporal;
import java.util.Calendar;

import static org.junit.Assert.assertEquals;

public class FlightTest {



    @Test
    public void getDurationShouldReturnCorrectValue(){
        Calendar departure = Calendar.getInstance();
        departure.set(2017, 2, 3, 10, 30, 0);

        Calendar arrival = Calendar.getInstance();
        arrival.set(2017, 2, 3, 12, 0, 0);

        Flight flight = new Flight();
        flight.setDepartureTime(departure.getTime());
        flight.setArrivalTime(arrival.getTime());


        Duration expected = Duration.of(90, ChronoUnit.MINUTES);
        assertEquals(expected, flight.getDuration());
    }
}
