package com.realdolmen.air.domain;

import org.hibernate.validator.constraints.NotBlank;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
@NamedQueries({
        @NamedQuery(name = Airport.FIND_ALL_ACTIVE, query = "SELECT a FROM Airport a where a.available = TRUE"),
        @NamedQuery(name = Airport.FIND_ALL, query = "SELECT a FROM Airport a")
})
public class Airport extends AbstractEntity {
    public static final String FIND_ALL = "Airport.findAll";
    public static final String FIND_ALL_ACTIVE = "Airport.findAllActive";

    /**
     * International Airport Code
     *
     * ICAO or IATA airport code consist of maximum 4 characters.
     */
    @Size(max=4)
    @NotBlank
    private String code;

    @NotBlank
    private String name;

    @Size(max=200)
    @Column(length=200)
    private String country;

    @NotNull
    private Boolean available = false;

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
