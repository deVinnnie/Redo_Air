package com.realdolmen.air.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.enterprise.inject.Produces;
import javax.enterprise.inject.spi.InjectionPoint;

public class LoggerProducer {
    @Produces
    public Logger create(InjectionPoint ip){
        System.out.println(ip.getBean().getBeanClass());
        return LoggerFactory.getLogger(ip.getBean().getBeanClass());
    }
}
