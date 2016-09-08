package com.realdolmen.air.service;

import com.realdolmen.air.domain.Customer;
import com.realdolmen.air.domain.Passenger;
import com.realdolmen.air.domain.TravelClass;
import com.realdolmen.air.domain.payement.Endorsement;
import com.realdolmen.air.repository.BookingRepository;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import java.math.BigDecimal;
import java.util.Arrays;

@RunWith(MockitoJUnitRunner.class)
public class BookingServiceTest {

    @Mock
    private PaymentService service;

    @Mock
    private BookingRepository bookingRepository;

    @InjectMocks
    private BookingService bookingService;

    private Customer customer = new Customer("River", "Tam");

    @Before
    public void setupMox() {
    }

    /**
     *
     * TODO: More comprehensive.
     */
    @Test
    public void testDoBookingWithOnePassenger(){
        bookingService.setUp();

        bookingService.setCustomer(customer);
        bookingService.setNumberOfSeats(1);
        bookingService.setTravelClass(
                new TravelClass(
                        "Business",
                        100,
                        new BigDecimal("10.0"),
                        null
                )
        );
        bookingService.setPassengers(
                Arrays.asList(
                        new Passenger("Harry", "Potter")
                )
        );

        bookingService.setPaymentMethod(new Endorsement());

        bookingService.doBooking();

        System.out.println(bookingService.getBooking().getId());
    }
}
