package com.realdolmen.course.domain;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class AccountNumberValidator implements ConstraintValidator<ValidAccountNumber, AccountNumber>{

    @Override
    public void initialize(ValidAccountNumber constraintAnnotation) {
    }


    @Override
    public boolean isValid(AccountNumber accountNumber, ConstraintValidatorContext context) {
        if(accountNumber.getBankCode() < 0 || accountNumber.getBankCode() > 999
                || accountNumber.getAccount() < 0 || accountNumber.getAccount() > 99_999_999
                || accountNumber.getControl() < 0 || accountNumber.getControl() > 99){
            return false;
        }


        long bankCode = accountNumber.getBankCode();
        long accountCode = accountNumber.getAccount();

        bankCode*=10_0000_000;
        long total = bankCode+accountCode;

        long remainder = total / 97L;
        long controlNumber = (remainder == 0) ? 97L : remainder;

        return (controlNumber == accountNumber.getControl());
    }


}
