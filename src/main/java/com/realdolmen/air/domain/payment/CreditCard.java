package com.realdolmen.air.domain.payment;

import org.hibernate.validator.constraints.CreditCardNumber;
import org.hibernate.validator.constraints.NotBlank;

import javax.persistence.Entity;
import javax.validation.constraints.Future;
import java.util.Date;

/**
 * See http://www.getcreditcardnumbers.com/ for examples
 * of valid credit card numbers.
 *
 */
@Entity
public class CreditCard extends PaymentMethod{

    @NotBlank(message = "{validation.credit-card.blank}")
    @CreditCardNumber(message = "{validation.credit-card.number}")
    private String number;

    @Future(message = "{validation.credit-card.future}")
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
