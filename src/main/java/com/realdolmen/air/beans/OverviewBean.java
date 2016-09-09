package com.realdolmen.air.beans;

import com.realdolmen.air.domain.Booking;
import com.realdolmen.air.domain.Customer;
import com.realdolmen.air.service.CustomerServiceBean;
import com.realdolmen.air.service.OverviewBookingService;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import java.io.Serializable;
import java.security.Principal;
import java.util.List;


@ManagedBean
public class OverviewBean implements Serializable{
    @Inject
    private OverviewBookingService overviewBookingService;

    @Inject
    private CustomerServiceBean customerService;

    private List<Booking> bookings;

    @PostConstruct()
    public void setUp(){
        Principal userPrincipal = FacesContext.getCurrentInstance().getExternalContext().getUserPrincipal();
        Customer customer = customerService.findCustomerByEmail(userPrincipal.getName()).get(0);

        bookings = overviewBookingService.findBookingsByCustomerId(customer.getId());
    }

    public List<Booking> getBookings() {
        return bookings;
    }

    public void setBookings(List<Booking> bookings) {
        this.bookings = bookings;
    }
}
