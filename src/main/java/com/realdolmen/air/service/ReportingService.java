package com.realdolmen.air.service;

import com.realdolmen.air.domain.Booking;
import com.realdolmen.air.domain.BulkDiscount;
import com.realdolmen.air.domain.TravelClass;
import com.realdolmen.air.repository.BookingRepository;
import com.realdolmen.air.repository.TravelClassRepositoryInterface;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.ejb.Schedule;
import javax.ejb.Singleton;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

/**
 * A service which generates reports about sales and profit.
 *
 * Data is gathered at interval via {@link #generateReports()}
 * and then stored as instance variables. This is to avoid unnecessary
 * calls to the database.
 *
 */
@Singleton
public class ReportingService {

    @EJB
    private BookingRepository bookingRepository;

    @EJB
    private TravelClassRepositoryInterface travelClassRepository;

    private Logger logger = LoggerFactory.getLogger(ReportingService.class);

    /**
     * Date at which the reports where last regenerated.
     */
    private Date lastUpdated;

    private BigDecimal totalSoldPrice = new BigDecimal("0.00");

    private BigDecimal totalPurchasePrice = new BigDecimal("0.00");

    public BigDecimal getTotalSoldPrice() {
        return totalSoldPrice;
    }

    public BigDecimal getTotalPurchasePrice() {
        return totalPurchasePrice;
    }

    public Date getLastUpdated() {
        return lastUpdated;
    }

    @PostConstruct
    public void setUp(){
        this.generateReports();
    }

    @Schedule(hour="*", minute = "*/2")
    public void generateReports() {
        generateTotalSold();
        generateTotalPurchased();

        this.lastUpdated = new Date();

        logger.info(
                String.format("Reports regenerated at %s", lastUpdated.toString())
        );
    }

    public void generateTotalPurchased() {
        List<TravelClass> travelClasses = travelClassRepository.findAll();

        BigDecimal total = new BigDecimal("0.00");
        for(TravelClass travelClass : travelClasses){
            BigDecimal purchasePriceForEntireClass =
                    travelClass.getBasePrice().multiply(
                        new BigDecimal(travelClass.getNumberOfSeatsBooked())
                    );


            // Discounts are ordered descending by minimum seats.
            BulkDiscount finalDiscount = null;
            for(BulkDiscount discount : travelClass.getBulkDiscounts()){
               if(discount.getMinimumSeats() <= travelClass.getNumberOfSeatsBooked()){
                   finalDiscount = discount;
                   break;
               }
            }

            if(finalDiscount != null){
                purchasePriceForEntireClass =
                        purchasePriceForEntireClass.subtract(
                                purchasePriceForEntireClass.multiply(finalDiscount.getDiscountPercentage())
                        );
            }

            total = total.add(purchasePriceForEntireClass);
        }

        this.totalPurchasePrice = total;
    }

    public void generateTotalSold(){
        List<Booking> bookings = bookingRepository.findAll();

        BigDecimal total = new BigDecimal("0.00");
        for (Booking b : bookings) {
            total = total.add(b.getTotalPrice());
        }

        this.totalSoldPrice = total;
    }
}
