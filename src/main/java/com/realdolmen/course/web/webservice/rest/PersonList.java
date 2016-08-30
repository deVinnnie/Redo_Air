package com.realdolmen.course.web.webservice.rest;

import com.realdolmen.course.domain.Person;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;

/**
 * This is a wrapper so that JAXB can create a <code>&lt;people&gt;</code> tag out of a <code>java.util.List&lt;People&gt;</code>.
 */
@XmlRootElement(name = "people")
@XmlAccessorType(XmlAccessType.FIELD)
public class PersonList {

    @XmlElement(name = "person")
    private List<Person> persons;

    public PersonList() {
    }

    public PersonList(List<Person> persons) {
        this.persons = persons;
    }

    public List<Person> getPersons() {
        return persons;
    }

    public void setPersons(List<Person> persons) {
        this.persons = persons;
    }
}
