package com.realdolmen.course.domain;

import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

public class PersonTest {
    @Test
    public void nameReturnsConcatenationOfFirstNameAndLastName() throws Exception {
        assertEquals("George Orwell", new Person("George", "Orwell").name());
    }

    @Test
    public void personIsInstantiatedWithNullId() throws Exception {
        assertNull("Person ID is supposed to be null before saving", new Person("Paul", "McCartney").getId());
    }
}
