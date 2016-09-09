package com.realdolmen.air.service;

import com.realdolmen.air.domain.Customer;
import com.realdolmen.air.domain.Passenger;
import com.realdolmen.air.domain.TravelClass;
import com.realdolmen.air.domain.payment.Endorsement;
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
public class BookingServiceBeanTest {

    @Mock
    private PaymentService service;

    @Mock
    private BookingRepository bookingRepository;

    @InjectMocks
    private BookingServiceBean bookingServiceBean;

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
        bookingServiceBean.setUp();

        bookingServiceBean.setCustomer(customer);
        bookingServiceBean.setNumberOfSeats(1);
        bookingServiceBean.setTravelClass(
                new TravelClass(
                        "Business",
                        100,
                        new BigDecimal("10.0"),
                        null
                )
        );
        bookingServiceBean.setPassengers(
                Arrays.asList(
                        new Passenger("Harry", "Potter")
                )
        );

        bookingServiceBean.setPaymentMethod(new Endorsement());

        bookingServiceBean.doBooking();

        System.out.println(bookingServiceBean.getBooking().getId());
    }
}
