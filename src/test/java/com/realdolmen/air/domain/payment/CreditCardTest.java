package com.realdolmen.air.domain.payment;

import com.realdolmen.air.domain.BeanValidatorTest;
import org.junit.Before;
import org.junit.Test;

import javax.validation.ConstraintViolation;
import java.util.Calendar;
import java.util.Date;
import java.util.Set;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class CreditCardTest extends BeanValidatorTest{

    private CreditCard creditCard;

    private static String VALID_CREDIT_CARD_NUMBER = "4916515338333836";

    private Date futureDate;
    private Date pastDate;

    @Before
    public void setUp(){
        super.setUp();

        Calendar c = Calendar.getInstance();
        c.set(2018,5,0);
        futureDate = c.getTime();

        Calendar c2 = Calendar.getInstance();
        c2.set(1999,1,0);
        pastDate = c2.getTime();

        creditCard = new CreditCard(VALID_CREDIT_CARD_NUMBER, futureDate);
    }

    @Test
    public void validCreditCardShouldGiveNoErrors(){
        Set<ConstraintViolation<CreditCard>> violations = getValidator().validate(creditCard);
        assertTrue(violations.isEmpty());
    }

    @Test
    public void invalidCreditCardNumberShouldGiveValidationErrors(){
        creditCard.setNumber("1212121212121212");
        Set<ConstraintViolation<CreditCard>> violations = getValidator().validate(creditCard);
        assertFalse(violations.isEmpty());
    }

    @Test
    public void creditCardNumberWithAlphaNumericSignsShouldGiveValidationErrors(){
        creditCard.setNumber("49165A533833B83C");
        Set<ConstraintViolation<CreditCard>> violations = getValidator().validate(creditCard);
        assertFalse(violations.isEmpty());
    }

    @Test
    public void emptyCreditCardNumberShouldGiveValidationErrors(){
        creditCard.setNumber("");
        Set<ConstraintViolation<CreditCard>> violations = getValidator().validate(creditCard);
        assertFalse(violations.isEmpty());
    }

    @Test
    public void creditCardNumberNullShouldGiveValidationErrors(){
        creditCard.setNumber(null);
        Set<ConstraintViolation<CreditCard>> violations = getValidator().validate(creditCard);
        assertFalse(violations.isEmpty());
    }

    @Test
    public void expiryDateInPastShouldGiveValidationErrors(){
        creditCard.setExpiryDate(pastDate);
        Set<ConstraintViolation<CreditCard>> violations = getValidator().validate(creditCard);
        assertFalse(violations.isEmpty());
    }
}
