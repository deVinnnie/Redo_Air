package com.realdolmen.course.web.controller;

import com.realdolmen.course.repository.PassengerRepository;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;

@Named
@RequestScoped
public class SearchPassengerBean {

    @Inject
    private PassengerRepository passengerRepository;


}
