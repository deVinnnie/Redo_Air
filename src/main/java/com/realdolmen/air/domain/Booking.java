package com.realdolmen.air.domain;

import com.realdolmen.air.domain.payement.Payment;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.List;

@Entity
public class Booking extends AbstractEntity {

    @OneToMany
    private List<Ticket> tickets;

    @OneToOne
    private Payment payment;

    @Embedded
    private Discount discount;

    public List<Ticket> getTickets() {
        return tickets;
    }

    public void setTickets(List<Ticket> tickets) {
        this.tickets = tickets;
    }

    public Payment getPayment() {
        return payment;
    }

    public void setPayment(Payment payment) {
        this.payment = payment;
    }

    public Discount getDiscount() {
        return discount;
    }

    public void setDiscount(Discount discount) {
        this.discount = discount;
    }

    /**
     * Calculates the total price before discounts are
     * taken into consideration.
     *
     * @return Total price (before discounts)
     */
    public BigDecimal getTotalPriceWithoutDiscount(){
        BigDecimal total = new BigDecimal("0.00");

        for(Ticket ticket : tickets){
            total.add(ticket.getBuyPrice());
        }

        return total;
    }

    /**
     * Calculates the total price including discounts.
     *
     * @return Total price (after discount)
     */
    public BigDecimal getTotalPrice(){
        BigDecimal totalWithoutDiscount = this.getTotalPriceWithoutDiscount();
        return this.getTotalPrice(totalWithoutDiscount);
    }

    protected BigDecimal getTotalPrice(BigDecimal totalWithoutDiscount){
        BigDecimal total = totalWithoutDiscount.subtract(
                totalWithoutDiscount.multiply(discount.getDiscountPercentage())
        );

        return total;
    }
}
