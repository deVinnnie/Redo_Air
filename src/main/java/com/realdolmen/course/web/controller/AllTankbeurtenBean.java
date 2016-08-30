package com.realdolmen.course.web.controller;



import com.realdolmen.course.domain.Tankbeurt;
import com.realdolmen.course.repository.TankbeurtRepository;

import javax.annotation.PostConstruct;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;
import java.util.List;

@Named
@RequestScoped
public class AllTankbeurtenBean {

    @Inject
    private TankbeurtRepository tankbeurtRepository;

    private List<Tankbeurt> tankbeurten;


    @PostConstruct
    public void setUp(){
        this.tankbeurten = tankbeurtRepository.findAll();
    }

    public List<Tankbeurt> getTankbeurten() {
        return tankbeurten;
    }

    public void setTankbeurten(List<Tankbeurt> tankbeurten) {
        this.tankbeurten = tankbeurten;
    }
}
