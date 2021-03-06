package com.realdolmen.air.domain;

import javax.persistence.*;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.Digits;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.util.List;

/**
 * TODO: Abstract price calculation away into a strategy.
 *
 */
@Entity
@NamedQueries({
    @NamedQuery(name = TravelClass.FIND_CLASSES_FROM_FLIGHT, query = "SELECT t from TravelClass t where " +
            "t.remainingSeats >= :numberOfSeats " +
            "and t.flight.id = :flightId order by t.name desc")
})
public class TravelClass extends AbstractEntity{
    public static final String FIND_CLASSES_FROM_FLIGHT = "TravelClass.findClassesFromFlight";

    @ManyToOne
    @JoinColumn(name="flight_id")
    private Flight flight;

    /**
     * The default profit margin defined as a multiplier.
     *
     * Example: A value of 1.05 corresponds to a profit margin of 5%.
     */
    @Transient
    private BigDecimal margin = new BigDecimal("1.05");

    /**
     * Name of the travel class.
     * Example: Business, First Class, Economy
     */
    @NotNull
    private String name;

    /**
     * Number of seats still available for purchase.
     */
    @DecimalMin("0.00")
    @NotNull
    private Integer remainingSeats;

    /**
     * Base price in euro's set by the Airline Company.
     */
    @DecimalMin("0.00")
    @Digits(integer = 12, fraction = 2)
    @NotNull
    private BigDecimal basePrice = new BigDecimal("0.00");

    /**
     * An overridden price (specified in euro's) set by ReDo Air.
     *
     * If this field is set then the consumer will pay this amount
     * for his ticket instead of the base price * margin set by the Airline.
     */
    @DecimalMin("0.00")
    @Digits(integer = 12, fraction = 2)
    private BigDecimal overriddenPrice = null;

    @OneToMany(mappedBy = "travelClass")
    private List<Ticket> tickets;

    /**
     * A list of discounts associated to this travel class.
     */
    @ElementCollection(fetch = FetchType.EAGER)
    @CollectionTable(name = "BulkDiscount")
    @OrderBy("minimumSeats DESC")
    @Column(name = "flight")
    private List<BulkDiscount> bulkDiscounts;

    public TravelClass() {
    }

    public TravelClass(String name, Integer remainingSeats, BigDecimal basePrice, BigDecimal overriddenPrice) {
        this.name = name;
        this.remainingSeats = remainingSeats;
        this.basePrice = basePrice;
        this.overriddenPrice = overriddenPrice;
    }

    //<editor-fold desc="Getters & Setters">
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getRemainingSeats() {
        return remainingSeats;
    }

    public void setRemainingSeats(Integer remainingSeats) {
        this.remainingSeats = remainingSeats;
    }

    public BigDecimal getBasePrice() {
        return basePrice;
    }

    public void setBasePrice(BigDecimal basePrice) {
        this.basePrice = basePrice;
    }

    public BigDecimal getOverriddenPrice() {
        return overriddenPrice;
    }

    public void setOverriddenPrice(BigDecimal overriddenPrice) {
        this.overriddenPrice = overriddenPrice;
    }


    public Flight getFlight() {
        return flight;
    }

    public void setFlight(Flight flight) {
        this.flight = flight;
    }

    public List<Ticket> getTickets() {
        return tickets;
    }

    public void setTickets(List<Ticket> tickets) {
        this.tickets = tickets;
    }

    public List<BulkDiscount> getBulkDiscounts() {
        return bulkDiscounts;
    }

    public void setBulkDiscounts(List<BulkDiscount> bulkDiscounts) {
        this.bulkDiscounts = bulkDiscounts;
    }
    //</editor-fold>

    // Make package protected. These should only be changed by Test cases.
    public BigDecimal getMargin() {
        return margin;
    }

    void setMargin(BigDecimal margin) {
        this.margin = margin;
    }

    /**
     * Calculates the price the customer has to pay for a ticket of this travel class.
     * The calculation is based on the base price, margin, and overridden price (if set).
     *
     * If the base price is null, then the method will return null.
     * This should however be avoided..
     *
     * This price does not include any additional discounts given the ReDo Air!
     *
     * @return Price in euro's.
     */
    public BigDecimal getEndUserPrice(){
        if(overriddenPrice != null){
            return overriddenPrice;
        }
        else if(basePrice != null){
            BigDecimal price = basePrice.multiply(margin);
            return price;
        }
        else{
            return null;
        }
    }

    /**
     * @return The number of seats that are 'sold' / 'booked'.
     */
    public Integer getNumberOfSeatsBooked(){
        return this.getTickets().size();
    }

    /**
     * Calculate the number of seats remaining.
     * @return Number of seats still available.
     */
    private Integer getNumberOfSeatsAvailable(){
        return 0;
    }
}