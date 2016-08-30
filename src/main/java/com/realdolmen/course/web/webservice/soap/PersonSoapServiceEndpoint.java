package com.realdolmen.course.web.webservice.soap;

import com.realdolmen.course.domain.Person;
import com.realdolmen.course.service.PersonServiceBean;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.jws.WebMethod;
import javax.jws.WebService;
import java.util.List;

/**
 * This is an endpoint for a JAX-WS SOAP web service.
 */
@WebService(serviceName="PersonSoapService", portName = "PersonWebService")
@Stateless
public class PersonSoapServiceEndpoint implements PersonSoapService {
    @EJB
    PersonServiceBean personServiceBean;

    @Override
    @WebMethod
    public List<Person> findAll() {
        return personServiceBean.findAll();
    }
}
