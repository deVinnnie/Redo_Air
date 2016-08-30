package com.realdolmen.course.repository;

import com.realdolmen.course.domain.Ticket;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Stateless
public class TicketRepository {

    @PersistenceContext
    EntityManager em;

    public Ticket save(Ticket ticket){
        em.persist(ticket);
        return ticket;
    }

    public Ticket update(Ticket ticket){
        ticket = em.merge(ticket);
        return ticket;
    }

    public void remove(Ticket ticket){
        ticket = em.merge(ticket);
        em.remove(ticket);
    }

    public Ticket refresh(Ticket ticket){
        em.refresh(ticket);
        return ticket;
    }

    public Ticket findById(Long id){
        return em.find(Ticket.class, id);
    }

    public List<Ticket> findAll(){
        return em.createQuery("SELECT t FROM Ticket t", Ticket.class)
                            .getResultList();

    }
}
