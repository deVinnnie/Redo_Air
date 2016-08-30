package com.realdolmen.course.service;

import com.realdolmen.course.domain.Tankbeurt;
import com.realdolmen.course.repository.TankbeurtRepository;

import javax.ejb.Stateless;
import javax.inject.Inject;

@Stateless
public class TankbeurtService {

    @Inject
    private TankbeurtRepository tankbeurtRepository;

    public void save(Tankbeurt tankbeurt){
        tankbeurtRepository.save(tankbeurt);
    }

}
