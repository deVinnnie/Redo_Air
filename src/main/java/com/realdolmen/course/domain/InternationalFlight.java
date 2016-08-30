package com.realdolmen.course.domain;

import javax.persistence.Entity;

@Entity
public class InternationalFlight extends Flight{
    private Boolean blacklisted;
    private String regulations;

    public Boolean getBlacklisted() {
        return blacklisted;
    }

    public void setBlacklisted(Boolean blacklisted) {
        this.blacklisted = blacklisted;
    }

    public String getRegulations() {
        return regulations;
    }

    public void setRegulations(String regulations) {
        this.regulations = regulations;
    }
}
