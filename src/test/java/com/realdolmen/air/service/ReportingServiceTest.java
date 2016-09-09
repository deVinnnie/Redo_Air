package com.realdolmen.air.service;

import com.realdolmen.air.domain.BulkDiscount;
import com.realdolmen.air.domain.TravelClass;
import com.realdolmen.air.repository.BookingRepository;
import com.realdolmen.air.repository.TravelClassRepository;
import com.realdolmen.air.util.Asserter;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class ReportingServiceTest {

    @Mock
    private BookingRepository bookingRepository;

    @Mock
    private TravelClassRepository travelClassRepository;

    @InjectMocks
    private ReportingService reportingService;

    private ArrayList<TravelClass> travelClasses;

    @Mock
    private TravelClass travelClass1;

    @Mock
    private TravelClass travelClass2;

    private Asserter asserter = new Asserter();

    @Before
    public void setupMox(){
        when(travelClass1.getBasePrice()).thenReturn(new BigDecimal("150.00"));
        when(travelClass2.getBasePrice()).thenReturn(new BigDecimal("150.00"));

        when(travelClass1.getNumberOfSeatsBooked()).thenReturn(5);
        when(travelClass2.getNumberOfSeatsBooked()).thenReturn(5);

        when(travelClass1.getBulkDiscounts()).thenReturn(Collections.emptyList());

        when(travelClass2.getBulkDiscounts()).thenReturn(
                Arrays.asList(
                        new BulkDiscount(4, new BigDecimal("0.10")),
                        new BulkDiscount(2, new BigDecimal("0.05"))
                )
        );

        travelClasses = new ArrayList<>();
        travelClasses.add(travelClass1);
        travelClasses.add(travelClass2);

        when(travelClassRepository.findAll()).thenReturn(travelClasses);
    }

    @Test
    public void generateTotalPurchasePriceWithBulkDiscountShouldReturnCorrectResult(){
        reportingService.generateTotalPurchased();

        BigDecimal expected = new BigDecimal("1425.00");
        asserter.assertBigDecimalEqual(
            expected,
            reportingService.getTotalPurchasePrice()
        );
    }

    @Test
    public void generateTotalPurchasePriceWithNoBulkDiscountApplicableShouldReturnCorrectResult(){
        when(travelClass2.getNumberOfSeatsBooked()).thenReturn(1);

        reportingService.generateTotalPurchased();

        BigDecimal expected = new BigDecimal("900.00");
        asserter.assertBigDecimalEqual(
                expected,
                reportingService.getTotalPurchasePrice()
        );
    }
}
