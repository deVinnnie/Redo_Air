package com.realdolmen.course.service;

import com.realdolmen.course.domain.Address;
import com.realdolmen.course.domain.CreditCard;
import com.realdolmen.course.domain.Passenger;
import com.realdolmen.course.domain.Ticket;
import com.realdolmen.course.repository.PassengerRepository;
import com.realdolmen.course.repository.TicketRepository;

import javax.annotation.Resource;
import javax.ejb.Remove;
import javax.ejb.Stateful;
import javax.inject.Inject;
import javax.jms.JMSConnectionFactory;
import javax.jms.JMSContext;
import javax.jms.JMSPasswordCredential;
import javax.jms.Queue;
import java.util.ArrayList;
import java.util.List;

@Stateful
public class PassengerStatefulEJB implements  PassengerStatefulEJBRemote{

    @Inject
    @JMSConnectionFactory("java:/ConnectionFactory") // optional JNDI lookup name
    @JMSPasswordCredential(userName="administrator", password="Azerty123!") // if required
    private JMSContext context;

    @Resource(lookup = "java:jboss/exported/rd/queues/TicketQueue")
    private Queue queue;


    @Inject
    private PassengerRepository passengerRepository;

    @Inject
    private TicketRepository ticketRepository;

    private Passenger passenger;
    private List<Ticket> tickets = new ArrayList<>();
    private List<CreditCard> creditCardList = new ArrayList<>();

    public void createPassenger(Passenger passenger){
        this.passenger = passenger;
    }

    public void addAddress(Address address){
        this.passenger.setAddress(address);
    }

    public void addCreditCard(CreditCard creditCard){
        this.creditCardList.add(creditCard);
    }

    public void addTicket(Ticket ticket) {
        this.tickets.add(ticket);
        ticket.setPassenger(passenger);
    }

    @Remove
    public void checkOut(){
        if(passenger == null){
            throw new IllegalArgumentException("");
        }

        passenger.setCards(creditCardList);
        passenger.setTickets(tickets);


        passengerRepository.save(passenger);

        for(Ticket ticket : tickets) {
            ticketRepository.save(ticket);
            context.createProducer().send(queue, ticket);
        }
    }
}
