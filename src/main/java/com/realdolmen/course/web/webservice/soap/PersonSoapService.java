package com.realdolmen.course.web.webservice.soap;

import com.realdolmen.course.domain.Person;

import javax.jws.WebMethod;
import javax.jws.WebService;
import java.util.List;

/**
 * This annotation is required by JAX-WS, both on the interface as well as on the implementing class.
 */
@WebService
public interface PersonSoapService {
    @WebMethod
    List<Person> findAll();
}
