package com.realdolmen.air.service;

import com.realdolmen.air.domain.payement.CreditCard;

import javax.ejb.Stateless;
import java.math.BigDecimal;

@Stateless
public class PaymentService {

    public boolean doPayment(CreditCard card, BigDecimal amount){
        return true;
    }


}
