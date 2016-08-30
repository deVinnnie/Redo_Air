package com.realdolmen.course.service;

import com.realdolmen.course.domain.Silly;
import com.realdolmen.course.domain.NumberGenerator;
import org.slf4j.Logger;

import javax.annotation.PostConstruct;
import javax.enterprise.context.Conversation;
import javax.inject.Inject;
import javax.inject.Named;

@Named
public class BookService {
    @Inject @Silly
    private NumberGenerator numberGenerator;

    @Inject
    private Logger logger;


    @PostConstruct
    public void init(){
        logger.info("Hello World");

        System.out.println("NumberGenerator " + numberGenerator.getClass().getName());

        System.out.println("---------------------------------------------------------");
        System.out.println("---------------------------------------------------------");
        System.out.println("---------------------------------------------------------");
    }

    public String getGeneratorName(){
        return numberGenerator.getClass().getName();
    }




}
