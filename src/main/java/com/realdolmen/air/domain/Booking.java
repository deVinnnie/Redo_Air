package com.realdolmen.air.domain;

import com.realdolmen.air.domain.payement.Payment;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Booking extends AbstractEntity {

    @OneToMany
    private List<Ticket> tickets = new ArrayList<>();

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
            total = total.add(ticket.getBuyPrice());
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
        return this.getTotalPrice(totalWithoutDiscount, this.discount);
    }

    protected BigDecimal getTotalPrice(BigDecimal totalWithoutDiscount, Discount discount){
        BigDecimal total;
        if(discount == null){
            total = totalWithoutDiscount;
        }
        else {
            total = totalWithoutDiscount.subtract(
                    totalWithoutDiscount.multiply(discount.getDiscountPercentage())
            );
        }
        return total;
    }

    /**
     * Since all tickets are the same price it is ok to pick the first one and return its price.
     *
     * @return The price per ticket.
     */
    public BigDecimal getPricePerTicket(){
        if(this.tickets.isEmpty()){
            return null;
        }
        return this.tickets.get(0).getBuyPrice();
    }
}
