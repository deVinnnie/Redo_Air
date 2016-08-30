package com.realdolmen.course.web.webservice.rest;

import com.realdolmen.course.domain.Person;
import com.realdolmen.course.utilities.integration.RemoteIntegrationTest;
import org.junit.Before;
import org.junit.Test;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;
import java.util.function.Consumer;

public class PersonRestServiceIntegrationTest extends RemoteIntegrationTest {
    private static final String REST_SERVICE_URL = "http://localhost:8080/jee7-starter/rest/people";

    private WebTarget target;

    private Consumer<PersonList> sharedAssertions = (list) -> {
        assertEquals(2, list.getPersons().size());
        list.getPersons().stream().map(Person::toString).forEach(logger::trace);
    };

    @Before
    public void initializeClient() {
        Client client = ClientBuilder.newClient();
        target = client.target(REST_SERVICE_URL);
    }

    @Test
    public void shouldRetrieveAllPersonsInXML() throws Exception {
        assertForRepresentation("get", MediaType.APPLICATION_XML_TYPE, PersonList.class, sharedAssertions);
    }

    @Test
    public void shouldRetrieveAllPersonsInJSON() throws Exception {
        assertForRepresentation("get", MediaType.APPLICATION_JSON_TYPE, PersonList.class, sharedAssertions);
    }

    private <T> void assertForRepresentation(String method, MediaType mediaType, Class<T> responseType, Consumer<T> assertions) {
        T result = target.request(mediaType).method(method, responseType);
        assertNotNull(result);
        assertions.accept(result);
    }
}
