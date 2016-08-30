package com.realdolmen.course.service.jms;

import com.realdolmen.course.domain.Person;
import com.realdolmen.course.domain.Ticket;
import com.realdolmen.course.service.PersonServiceBean;
import org.omg.CORBA.Object;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.ejb.ActivationConfigProperty;
import javax.ejb.EJB;
import javax.ejb.MessageDriven;
import javax.jms.*;

@MessageDriven(activationConfig = {
        @ActivationConfigProperty(propertyName = "destinationType", propertyValue = "javax.jms.Queue"),
        @ActivationConfigProperty(propertyName = "destination", propertyValue = "java:jboss/exported/rd/queues/TicketQueue"),
        @ActivationConfigProperty(propertyName = "acknowledgeMode", propertyValue = "Auto-acknowledge")
})
public class TicketMessageDrivenBean implements MessageListener {
    private Logger logger = LoggerFactory.getLogger(getClass());

    @EJB
    PersonServiceBean personService;

    @Override
    public void onMessage(Message message) {
        ObjectMessage objMessage = (ObjectMessage) message;
        try {
            Ticket ticket = (Ticket) objMessage.getObject();
            logger.info("Ticket with price " + ticket.getPrice() + " Arrived at the TicketMessageDrivenBean.");
        } catch (JMSException e) {
            throw new RuntimeException("Unable to receive JMS message", e);
        }
    }
}
