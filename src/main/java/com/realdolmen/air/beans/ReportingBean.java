package com.realdolmen.air.beans;

import com.realdolmen.air.service.ReportingService;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;
import java.math.BigDecimal;
import java.util.Date;

@Named
@RequestScoped
public class ReportingBean {

    @Inject
    private ReportingService service;

    public BigDecimal getTotalSold(){
        return service.getTotalSoldPrice();
    }

    public BigDecimal getTotalPurchased() {
        return service.getTotalPurchasePrice();
    }

    public Date getLastUpdated(){
        return service.getLastUpdated();
    }
}
