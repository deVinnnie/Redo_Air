package com.realdolmen.course.web.controller;

import javax.annotation.PostConstruct;
import javax.enterprise.context.RequestScoped;
import javax.inject.Named;
import java.util.HashMap;

@Named
@RequestScoped
public class FinancialResults {
    HashMap<String, Integer> financialResults = new HashMap<>();

    @PostConstruct
    public void setUp(){
        this.financialResults.put("Infrastructure", 500);
        this.financialResults.put("Sales", -1200);
        this.financialResults.put("Education", 1200);
        this.financialResults.put("Marketing", 320);
        this.financialResults.put("Java", 600);
        this.financialResults.put(".NET", -250);
    }

    public HashMap<String, Integer> getFinancialResults() {
        return financialResults;
    }

    public void setFinancialResults(HashMap<String, Integer> financialResults) {
        this.financialResults = financialResults;
    }
}
