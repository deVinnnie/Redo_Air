package com.realdolmen.course.service;

import com.realdolmen.course.domain.Person;
import com.realdolmen.course.utilities.integration.RemoteIntegrationTest;
import org.junit.Test;

import java.util.List;

public class PersonServiceRemoteTest extends RemoteIntegrationTest {
    @Test
    public void testPersonServiceCanBeAccessedRemotely() throws Exception {
        PersonServiceRemote personService = lookup("jee7-starter/PersonServiceBean!com.realdolmen.course.service.PersonServiceRemote");
        List<Person> people = personService.findAll();
        assertEquals(2, people.size());
        for (Person person : people) {
            logger.trace("Retrieved person " + person);
        }
    }
}
