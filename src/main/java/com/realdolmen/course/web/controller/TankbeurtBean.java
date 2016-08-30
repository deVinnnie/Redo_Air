package com.realdolmen.course.web.controller;

import com.realdolmen.course.domain.Tankbeurt;
import com.realdolmen.course.service.TankbeurtService;


import javax.annotation.PostConstruct;
import javax.faces.bean.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import java.util.Calendar;

@Named
@ViewScoped
public class TankbeurtBean {

    private Tankbeurt tankbeurt;

    @Inject
    private TankbeurtService tankbeurtService;

    @PostConstruct
    public void setUp(){
        tankbeurt = new Tankbeurt("AAA-153", 20.5, 18.5, 15.5, Calendar.getInstance().getTime());
    }


    public Tankbeurt getTankbeurt() {
        return tankbeurt;
    }

    public void setTankbeurt(Tankbeurt tankbeurt) {
        this.tankbeurt = tankbeurt;
    }

    public String saveTankbeurt(){
        tankbeurtService.save(tankbeurt);
        return "tankbeurten.xhtml";
    }
}
