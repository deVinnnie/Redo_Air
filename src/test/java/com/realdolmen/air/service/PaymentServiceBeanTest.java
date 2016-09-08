package com.realdolmen.air.service;

import com.realdolmen.air.domain.payement.CreditCard;
import org.junit.Before;
import org.junit.Test;

import java.math.BigDecimal;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.verify;

public class PaymentServiceBeanTest {
    private PaymentService paymentService;
    private CreditCard creditCard;
    @Before
    public void setUp(){
        paymentService = new PaymentService();
        creditCard = new CreditCard();
    }
    @Test
    public void test_getAllAirportsShouldReturnAll() {
        boolean result = paymentService.doPayment(creditCard,new BigDecimal(2));
        assertTrue(result);
    }
}
