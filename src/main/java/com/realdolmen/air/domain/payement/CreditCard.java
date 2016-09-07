package com.realdolmen.air.domain.payement;

import org.hibernate.validator.constraints.CreditCardNumber;
import org.hibernate.validator.constraints.NotBlank;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.validation.constraints.Future;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.Date;

@Entity
public class CreditCard extends PaymentMethod{

    @NotBlank
    @CreditCardNumber
    private String number;

    @Future
    private Date expiryDate;

    @Min(0)
    @NotNull
    private Integer controlNumber;

    public CreditCard() {}

    public CreditCard(String number, Date expiryDate, Integer controlNumber) {
        this.number = number;
        this.expiryDate = expiryDate;
        this.controlNumber = controlNumber;
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

    public Integer getControlNumber() {
        return controlNumber;
    }

    public void setControlNumber(Integer controlNumber) {
        this.controlNumber = controlNumber;
    }
}
