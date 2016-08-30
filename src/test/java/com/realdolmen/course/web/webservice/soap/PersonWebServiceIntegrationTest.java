package com.realdolmen.course.web.webservice.soap;

import com.realdolmen.course.domain.Person;
import com.realdolmen.course.utilities.integration.RemoteIntegrationTest;
import org.junit.Before;
import org.junit.Test;

import javax.xml.namespace.QName;
import javax.xml.ws.Service;
import java.net.URL;
import java.util.List;

public class PersonWebServiceIntegrationTest extends RemoteIntegrationTest {

    private PersonSoapService service;

    @Before
    public void setupBeforeClass() throws Exception {
        URL wsdlLocation = new URL("http://localhost:8080/jee7-starter/PersonSoapService/PersonSoapServiceEndpoint?wsdl");
        QName serviceName = new QName("http://soap.webservice.web.course.realdolmen.com/", "PersonSoapService");
        Service webService = Service.create(wsdlLocation, serviceName);
        service = webService.getPort(PersonSoapService.class);
    }

    @Test
    public void shouldReturnAllPeople() {
        List<Person> result = service.findAll();
        assertNotNull(result);
        assertFalse(result.isEmpty());
        result.stream().map(Person::toString).forEach(logger::trace);
    }
}
