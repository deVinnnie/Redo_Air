package com.realdolmen.course.domain;

import javax.persistence.Embeddable;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;

@Embeddable
public class AccountNumber {

    private Long bankCode;

    private Long account;

    private Long control;

    public AccountNumber() {}

    public AccountNumber(Long bankCode, Long account, Long control) {
        this.bankCode = bankCode;
        this.account = account;
        this.control = control;
    }

    public Long getBankCode() {
        return bankCode;
    }

    public void setBankCode(Long bankCode) {
        this.bankCode = bankCode;
    }

    public Long getAccount() {
        return account;
    }

    public void setAccount(Long account) {
        this.account = account;
    }

    public Long getControl() {
        return control;
    }

    public void setControl(Long control) {
        this.control = control;
    }

    public String toString(){
        //String.format();
        return this.getBankCode() + "-" + this.getAccount() + "-" + this.getControl();
    }
}
