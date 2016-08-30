package com.realdolmen.course.domain;

import com.realdolmen.course.service.PersonServiceBean;

import javax.ejb.EJB;
import javax.enterprise.context.Conversation;
import javax.enterprise.context.ConversationScoped;
import javax.enterprise.inject.TransientReference;
import javax.inject.Inject;
import javax.inject.Named;
import javax.persistence.Transient;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

@ConversationScoped
@Named("personSaver")
public class PersonSaver implements Serializable{

    private List<Person> list = new ArrayList<>();

    @EJB
    private PersonServiceBean personService;

    public List<Person> getItems(){
        return list;
    }

    @Inject
    private Conversation conversation;

    public void start(){
        if(conversation.isTransient()) {
            conversation.begin();
        }
    }

    public void doSomething(){
        this.start();

        System.out.println("Doing something");

        Person person = new Person("Dagoberth " + new Random(System.nanoTime()).nextInt(), "Duck " + new Random(System.nanoTime()).nextInt());

        list.add(person);
    }

    public void stop(){
        for(Person person : this.list){
            this.personService.save(person);
        }
        this.list.clear();

        if(!conversation.isTransient()) {
            conversation.end();
        }
    }
}
