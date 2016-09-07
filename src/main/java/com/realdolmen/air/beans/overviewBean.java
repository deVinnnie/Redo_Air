package com.realdolmen.air.beans;

import com.realdolmen.air.domain.*;
import com.realdolmen.air.domain.payement.CreditCard;
import com.realdolmen.air.domain.payement.Endorsement;
import com.realdolmen.air.service.BookingService;
import com.realdolmen.air.service.CustomerServiceBean;
import com.realdolmen.air.service.OverviewBookingService;
import com.realdolmen.air.web.controller.Phase;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.transaction.Transactional;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.security.Principal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;


@ManagedBean
@SessionScoped
public class overviewBean implements Serializable{
    @Inject
    private OverviewBookingService overviewBookingService;

    @Inject
    private CustomerServiceBean customerService;

    private Customer customer;

    private List<Booking> bookings;

    @PostConstruct()
    public void setUp(){
        Principal userPrincipal = FacesContext.getCurrentInstance().getExternalContext().getUserPrincipal();
        customer = customerService.findCustomerByEmail(userPrincipal.getName()).get(0);

        bookings = overviewBookingService.findBookingsByCustomerId(customer.getId());
    }

    public List<Booking> getBookings() {
        return bookings;
    }

    public void setBookings(List<Booking> bookings) {
        this.bookings = bookings;
    }
}
