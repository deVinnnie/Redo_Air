package com.realdolmen.course.service.jms;

import com.realdolmen.course.domain.Person;
import com.realdolmen.course.utilities.integration.RemoteJmsTest;
import org.junit.Test;

import javax.jms.JMSException;
import javax.jms.ObjectMessage;

public class PersonMessageDrivenBeanTest extends RemoteJmsTest {
    @Test
    public void shouldSendAPassengerMessage() throws JMSException {
        ObjectMessage message = session.createObjectMessage(new Person("Theo", "Tester"));
        producer.send(message);
        assertPatiently(() -> assertEquals(3, count(Person.class)));
    }
}
