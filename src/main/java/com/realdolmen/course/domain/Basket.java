package com.realdolmen.course.domain;


import org.slf4j.Logger;

import javax.enterprise.context.Conversation;
import javax.enterprise.context.ConversationScoped;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

@Named
@ConversationScoped
public class Basket implements Serializable{
    private List<String> list = new ArrayList<>();

    @Inject
    private Conversation conversation;

    @Inject
    private Logger logger;

    public List<String> getItems(){
        return list;
    }

    public void start(){
        if(conversation.isTransient()) {
            conversation.begin();
            logger.info("Started Conversation");
            //conversation.setTimeout(5000);
        }
    }

    public void doSomething(){
        this.start();


        System.out.println("Doing something");
        String item = "Item" + new Random(System.nanoTime()).nextInt(); // Performance Killer! Reinit random everytime is very expensive.

        list.add(item);
    }

    public void stop(){
        if(!conversation.isTransient()) {
            conversation.end();
            logger.info("Ended Conversation");
        }
    }
}
