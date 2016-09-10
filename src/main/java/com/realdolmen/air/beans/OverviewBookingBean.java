package com.realdolmen.air.beans;

import com.realdolmen.air.domain.Ticket;
import com.realdolmen.air.service.BookingService;
import com.realdolmen.air.service.TicketService;
import org.mindrot.jbcrypt.BCrypt;

import javax.faces.bean.ManagedBean;
import javax.inject.Inject;
import java.io.IOException;
import java.io.Serializable;
import java.util.List;

@ManagedBean
public class OverviewBookingBean implements Serializable {
    private Long bookingId;
    private List<Ticket> tickets;

    @Inject
    TicketService ticketService;

    public void onParametersLoaded() throws IOException {
        tickets = ticketService.findTicketsByBookingId(bookingId);

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
