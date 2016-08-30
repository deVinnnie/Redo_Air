package com.realdolmen.course.service.jms;

import com.realdolmen.course.domain.Person;
import com.realdolmen.course.domain.Ticket;
import com.realdolmen.course.utilities.integration.RemoteJmsTest;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import javax.jms.JMSException;
import javax.jms.ObjectMessage;
import javax.jms.TextMessage;

public class TicketMessageDrivenBeanTest extends RemoteJmsTest {

    @Override
    public void initializeJms() throws Exception {
        this.queueLookup = "rd/queues/TicketQueue";
        super.initializeJms();
    }

    @Test
    public void shouldSendAPassengerMessage() throws JMSException {
        ObjectMessage message = session.createObjectMessage(new Ticket(4.2));
        producer.send(message);

        //ObjectMessage

        /*ObjectMessage message = session.createObjectMessage(new Person("Theo", "Tester"));
        producer.send(message);*/
        //assertPatiently(() -> assertEquals(3, count(Person.class)));
    }
}
