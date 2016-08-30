package com.realdolmen.course.service.jms;

import com.realdolmen.course.domain.Person;
import com.realdolmen.course.service.PersonServiceBean;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.ejb.ActivationConfigProperty;
import javax.ejb.EJB;
import javax.ejb.MessageDriven;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.ObjectMessage;

@MessageDriven(activationConfig = {
        @ActivationConfigProperty(propertyName = "destinationType", propertyValue = "javax.jms.Queue"),
        @ActivationConfigProperty(propertyName = "destination", propertyValue = "java:jboss/exported/rd/queues/RealDolmenQueue"),
        @ActivationConfigProperty(propertyName = "acknowledgeMode", propertyValue = "Auto-acknowledge")
})
public class PersonMessageDrivenBean implements MessageListener {
    private Logger logger = LoggerFactory.getLogger(getClass());

    @EJB
    PersonServiceBean personService;

    @Override
    public void onMessage(Message message) {
        ObjectMessage om = (ObjectMessage) message;
        try {
            Person person = (Person) om.getObject();
            logger.info("Received JMS message for person " + person);
            personService.save(person);
        } catch (JMSException e) {
            throw new RuntimeException("Unable to receive JMS message", e);
        }
    }
}
