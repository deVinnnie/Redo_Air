package com.realdolmen.air.repository;

import com.realdolmen.air.domain.Booking;
import com.realdolmen.testutil.TestData;
import com.realdolmen.testutil.TestDataLocation;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.assertEquals;

/**
 * Created by knockaert on 5/04/2016.
 */
public class BookingRepositoryTest extends AbstractRepositoryTest<BookingRepository> {
    @Test
    @TestData(dataSet = TestDataLocation.BOOKING)
    public void findBookingsByCustomerIdShouldReturnCorrectBookings() {
        List<Booking> bookings = getRepository().findBookingsByCustomerId(Long.parseLong("1"));
        assertEquals(2, bookings.size());
    }

    @Test
    @TestData(dataSet = TestDataLocation.BOOKING)
    public void findBookingsByCustomerIdShouldReturnCorrectBookingsWrongId() {
        List<Booking> bookings = getRepository().findBookingsByCustomerId(Long.parseLong("100"));
        assertEquals(0, bookings.size());
    }

    @Test
    public void findBookingsByCustomerIdShouldReturnCorrectBookingsNoData() {
        List<Booking> bookings = getRepository().findBookingsByCustomerId(Long.parseLong("1"));
        assertEquals(0, bookings.size());
    }


}
