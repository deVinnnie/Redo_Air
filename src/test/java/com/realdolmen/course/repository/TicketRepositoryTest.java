package com.realdolmen.course.repository;

import com.realdolmen.course.domain.Passenger;
import com.realdolmen.course.domain.PassengerId;
import com.realdolmen.course.domain.Ticket;
import com.realdolmen.course.utilities.persistence.JpaPersistenceTest;
import org.junit.Before;
import org.junit.Test;

public class TicketRepositoryTest extends JpaPersistenceTest{

    private TicketRepository ticketRepository;
    private static final PassengerId TEST_PASSENGER_ID = new PassengerId("456487938", "Potter");
    private Passenger misterPotter;

    @Before
    public void setUp(){
        ticketRepository = new TicketRepository();
        ticketRepository.em = entityManager();

        misterPotter = entityManager().find(Passenger.class, TEST_PASSENGER_ID);
    }

    @Test
    public void testSaveTicketShouldSucceed(){
        assertEquals(0, ticketRepository.findAll().size());

        Ticket ticket = new Ticket(42.5, misterPotter, null, null);
        entityManager().persist(ticket);

        assertEquals(1, ticketRepository.findAll().size());
        assertNotNull(ticket.getId());
    }

    @Test
    public void testUpdateTicket(){
        Ticket ticket = new Ticket(42.5, misterPotter, null, null);
        entityManager().persist(ticket);
        entityManager().flush();
        entityManager().clear();

        ticket.setPrice(48.6);
        ticket = ticketRepository.update(ticket);

        assertEquals(new Double(48.6), ticket.getPrice());
    }

    @Test
    public void testFindTicketNonExistingGivesNull(){
        Ticket ticket = ticketRepository.findById(45565676L);
        assertNull(ticket);
    }

    @Test
    public void testFindTicketExistingTicketGivesCorrectResult() {
        Ticket ticket = new Ticket(42.5, misterPotter, null, null);
        entityManager().persist(ticket);

        Long id = ticket.getId();
        Ticket ticket2 = ticketRepository.findById(id);
        assertEquals(ticket, ticket2);
    }

    @Test
    public void testRemoveTicket(){
        Ticket ticket = new Ticket(42.5, misterPotter, null, null);
        entityManager().persist(ticket);
        Long id = ticket.getId();
        entityManager().flush();
        entityManager().clear();


        ticketRepository.remove(ticket);

        Ticket removedTicket = ticketRepository.findById(id);
        assertNull(removedTicket);
    }
}
