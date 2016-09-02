package com.realdolmen.air.domain;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.validation.constraints.Size;

@Entity
@NamedQueries({
        @NamedQuery(name = Airport.FIND_ALL, query = "SELECT a FROM Airport a")
})
public class Airport extends AbstractEntity {
    public static final String FIND_ALL = "Airport.findAll";

    /**
     * International Airport Code
     *
     * ICAO or IATA airport code consist of maximum 4 characters.
     */
    @Size(max=4)
    private String code;

    private String name;

    private String country;

    private Boolean available;

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

    public Boolean getAvailable() {
        return available;
    }

    public void setAvailable(Boolean available) {
        this.available = available;
    }
}
