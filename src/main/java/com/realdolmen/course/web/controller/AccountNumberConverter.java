package com.realdolmen.course.web.controller;

import com.realdolmen.course.domain.AccountNumber;

import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.ConverterException;
import javax.faces.convert.FacesConverter;

@FacesConverter(forClass = AccountNumber.class)
public class AccountNumberConverter implements Converter{

    @Override
    public Object getAsObject(FacesContext facesContext, UIComponent uiComponent, String s) {
        String[] splitted = s.split("-");
        if(splitted.length != 3){
            FacesMessage msg = new FacesMessage("Bad Account Number");
            throw new ConverterException(msg);
        }

        AccountNumber accountNumber = null;

        try {
            Long bankCode = Long.parseLong(splitted[0]);
            Long account = Long.parseLong(splitted[1]);
            Long control = Long.parseLong(splitted[2]);
            accountNumber = new AccountNumber(bankCode, account, control);
        }
        catch(NumberFormatException exception){
            FacesMessage msg = new FacesMessage("Bad Account Number");
            throw new ConverterException(msg);
        }

        return accountNumber;
    }

    @Override
    public String getAsString(FacesContext facesContext, UIComponent uiComponent, Object o) {
        return o.toString();
    }
}
