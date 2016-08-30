package com.realdolmen.course.domain;

import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import java.util.Date;
import java.util.List;

@Entity
public class DomesticFlight extends Flight{

    private String airlineCompany;

    @ElementCollection
    private List<String> referencesList;

    public DomesticFlight(String number, Date departureTime, Date arrivalTime) {
       super(number, departureTime, arrivalTime);
    }


    public String getAirlineCompany() {
        return airlineCompany;
    }

    public void setAirlineCompany(String airlineCompany) {
        this.airlineCompany = airlineCompany;
    }

    public List<String> getReferencesList() {
        return referencesList;
    }

    public void setReferencesList(List<String> referencesList) {
        this.referencesList = referencesList;
    }
}
