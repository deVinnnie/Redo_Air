package com.realdolmen.air.web.controller;

import org.apache.commons.lang3.tuple.ImmutablePair;
import org.apache.commons.lang3.tuple.ImmutableTriple;

import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.ManagedBean;
import java.util.Arrays;
import java.util.List;

/**
 * Convenience bean for generating navs on the admin pages.
 */
@ManagedBean
@ApplicationScoped
public class AdminNavBean {

    // Link name -> icon class
    private final List<ImmutableTriple<String, String, String>> links = Arrays.asList(
            new ImmutableTriple<>("airlines", "airlines.xhtml", "airline_seat_recline_normal"),
            new ImmutableTriple<>("airports", "airports.xhtml", "place"),
            new ImmutableTriple<>("flights", "flights.xhtml", "flight_takeoff"),
            new ImmutableTriple<>("reports", "reports.xhtml", "subject")
    );

    public List<ImmutableTriple<String, String, String>> getLinks() {
        return links;
    }
}
