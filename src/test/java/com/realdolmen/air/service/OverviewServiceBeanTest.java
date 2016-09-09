package com.realdolmen.air.service;

import com.realdolmen.air.domain.Airport;
import com.realdolmen.air.domain.Booking;
import com.realdolmen.air.repository.AirportRepository;
import com.realdolmen.air.repository.BookingRepository;
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

@RunWith(MockitoJUnitRunner.class)
public class OverviewServiceBeanTest {

    @Mock
    private BookingRepository bookingRepository;

    @InjectMocks
    private OverviewBookingService overviewBookingService;

    Booking booking;
    Booking booking2;

    List<Booking> bookings;

    @Before
    public void setupMox() {
        booking = new Booking();
        booking2 = new Booking();

        bookings = new ArrayList<>();
        bookings.add(booking);
        bookings.add(booking2);

        when(bookingRepository.findBookingsByCustomerId(1L)).thenReturn(bookings);
    }

    @Test
    public void findBookingByCustomerShouldReturnAll(){
        List<Booking> results = overviewBookingService.findBookingsByCustomerId(1L);

        assertNotNull(results);
        assertEquals(2, results.size());

        verify(bookingRepository).findBookingsByCustomerId(1L);
        verifyNoMoreInteractions(bookingRepository);
    }
}
