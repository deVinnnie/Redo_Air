package com.realdolmen.course.domain;

import javax.persistence.*;
import javax.validation.constraints.Min;
import javax.validation.constraints.Past;
import javax.validation.constraints.Size;
import java.util.Date;

@Entity
public class Tankbeurt {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Size(max = 50)
    private String nummerplaat;

    @Min(0)
    private Double nKilometers;

    @Min(0)
    private Double nLiter;

    @Min(0)
    private Double prijsPerLiter;

    @Temporal(TemporalType.TIMESTAMP)
    @Past
    private Date datum;

    public Tankbeurt() {
    }

    public Tankbeurt(String nummerplaat, Double nKilometers, Double nLiter, Double prijsPerLiter, Date datum) {
        this.nummerplaat = nummerplaat;
        this.nKilometers = nKilometers;
        this.nLiter = nLiter;
        this.prijsPerLiter = prijsPerLiter;
        this.datum = datum;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNummerplaat() {
        return nummerplaat;
    }

    public void setNummerplaat(String nummerplaat) {
        this.nummerplaat = nummerplaat;
    }

    public Double getnKilometers() {
        return nKilometers;
    }

    public void setnKilometers(Double nKilometers) {
        this.nKilometers = nKilometers;
    }

    public Double getnLiter() {
        return nLiter;
    }

    public void setnLiter(Double nLiter) {
        this.nLiter = nLiter;
    }

    public Double getPrijsPerLiter() {
        return prijsPerLiter;
    }

    public void setPrijsPerLiter(Double prijsPerLiter) {
        this.prijsPerLiter = prijsPerLiter;
    }

    public Date getDatum() {
        return datum;
    }

    public void setDatum(Date datum) {
        this.datum = datum;
    }
}
