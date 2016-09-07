package com.realdolmen.air.domain.payement;

import com.realdolmen.air.domain.AbstractEntity;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.OneToOne;

@Entity
public class Payment extends AbstractEntity{

    @Enumerated(EnumType.STRING)
    private PaymentStatus status;

    @OneToOne
    private PaymentMethod method;

    public PaymentStatus getStatus() {
        return status;
    }

    public void setStatus(PaymentStatus status) {
        this.status = status;
    }

    public PaymentMethod getMethod() {
        return method;
    }

    public void setMethod(PaymentMethod method) {
        this.method = method;
    }
}
