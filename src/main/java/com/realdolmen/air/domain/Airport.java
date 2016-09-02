package com.realdolmen.air.domain;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;

@Entity
@NamedQueries({
        @NamedQuery(name = Airport.FIND_ALL, query = "SELECT a FROM Airport a")
})
public class Airport extends AbstractEntity {
    public static final String FIND_ALL = "Airport.findAll";
    /**
     * International Airport Code
     */
    private String code;

    private String name;

    private String country;

    @ManyToOne
    private Region region;

    public Airport() {}

    public Airport(String code, String name) {
        this.code = code;
        this.name = name;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Region getRegion() {
        return region;
    }

    public void setRegion(Region region) {
        this.region = region;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }
}
