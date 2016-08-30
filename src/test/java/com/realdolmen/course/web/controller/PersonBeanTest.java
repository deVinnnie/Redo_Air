package com.realdolmen.course.web.controller;

import com.realdolmen.course.service.PersonServiceBean;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class PersonBeanTest extends Mockito {
    @Mock
    private PersonServiceBean personService;

    @InjectMocks
    private PersonBean controller = new PersonBean();

    @Test
    public void getAllPeopleDelegatesToBookService() throws Exception {
        controller.allPeople();
        verify(personService).findAll();
        verifyNoMoreInteractions(personService);
    }

    @Test
    public void removeDelegatesToPersonService() throws Exception {
        controller.remove(1507);
        verify(personService).remove(1507);
        verifyNoMoreInteractions(personService);
    }
}
