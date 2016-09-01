package com.realdolmen.air.service;

import com.realdolmen.air.domain.Flight;
import com.realdolmen.air.domain.Ticket;
import com.realdolmen.air.domain.TravelClass;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Stateless
public class TicketService {

    @PersistenceContext
    private EntityManager entityManager;

    public void calculateBuyPrice(){
        Flight flight = entityManager.find(Flight.class, 200L);

        List<TravelClass> list = new ArrayList<>(flight.getTravelClasses());

        TravelClass travelClass = list.get(0);

        BigDecimal basePrice = travelClass.getBasePrice();

        //BigDecimal marginMultiplier = TravelClass.DEFAULT_MARGIN.divide(new BigDecimal("100.000"));
       // BigDecimalmarginMultiplier = marginMultiplier.add(new BigDecimal("1.000"));
        //BigDecimal price = basePrice.multiply(marginMultiplier);

        Ticket ticket = new Ticket();
        ticket.setTravelClass(travelClass.getName());
        //ticket.setBuyPrice(price);
    }
}
