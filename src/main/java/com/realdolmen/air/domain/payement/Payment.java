package com.realdolmen.air.domain.payement;

import com.realdolmen.air.domain.AbstractEntity;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;

@Entity
public class Payment extends AbstractEntity{

    @Enumerated(EnumType.STRING)
    private PaymentStatus status;

    //private PayementMethod method;


    public PaymentStatus getStatus() {
        return status;
    }

    public void setStatus(PaymentStatus status) {
        this.status = status;
    }
}
