package com.realdolmen.air.beans;

import com.realdolmen.air.domain.Booking;
import com.realdolmen.air.domain.Customer;
import com.realdolmen.air.domain.Ticket;
import com.realdolmen.air.service.BookingService;
import com.realdolmen.air.service.TicketService;
import org.mindrot.jbcrypt.BCrypt;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.faces.bean.ManagedBean;
import javax.inject.Inject;
import java.io.IOException;
import java.io.Serializable;
import java.util.List;

@ManagedBean
public class OverviewBookingBean implements Serializable {
    private Long bookingId;
    private List<Ticket> tickets;

    private Logger logger = LoggerFactory.getLogger(OverviewBookingBean.class);

    @Inject
    TicketService ticketService;

    @Inject
    private RedirectionBean redirectionBean;

    @Inject
    private UserBean userBean;

    public void onParametersLoaded() throws IOException {
        Customer sessionCustomer = userBean.getUserAsCustomer();
        logger.info(sessionCustomer.getFirstName());

        if(sessionCustomer == null){
            logger.info("Null customer trying to access booking");
            redirectionBean.throw404();
            return;
        }

        Booking booking = ticketService.findBooking(bookingId);
        if(booking == null || !booking.getCustomer().getEmail().equals(sessionCustomer.getEmail())) {
            logger.info("Wrong customer trying to access booking");
            redirectionBean.throw404();
            return;
        }

        tickets = ticketService.findTicketsByBookingId(bookingId);
        if(tickets.isEmpty()){
            redirectionBean.throw404();
            return;
        }
    }

    public Long getBookingId() {
        return bookingId;
    }

    public void setBookingId(Long bookingId) {
        this.bookingId = bookingId;
    }

    public List<Ticket> getTickets() {
        return tickets;
    }

    public void setTickets(List<Ticket> tickets) {
        this.tickets = tickets;
    }

    public String encrypt(Long id){
        return BCrypt.hashpw(id.toString(),BCrypt.gensalt());
    }
}
