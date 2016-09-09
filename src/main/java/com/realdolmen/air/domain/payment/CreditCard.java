package com.realdolmen.air.domain.payment;

import org.hibernate.validator.constraints.CreditCardNumber;
import org.hibernate.validator.constraints.NotBlank;

import javax.persistence.Entity;
import javax.validation.constraints.Future;
import java.util.Date;

@Entity
public class CreditCard extends PaymentMethod{

    @NotBlank
    @CreditCardNumber
    private String number;

    @Future
    private Date expiryDate;

    public CreditCard() {}

    public CreditCard(String number, Date expiryDate) {
        this.number = number;
        this.expiryDate = expiryDate;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public Date getExpiryDate() {
        return expiryDate;
    }

    public void setExpiryDate(Date expiryDate) {
        this.expiryDate = expiryDate;
    }

}
