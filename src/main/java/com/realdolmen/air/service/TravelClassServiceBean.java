package com.realdolmen.air.service;

import com.realdolmen.air.domain.TravelClass;
import com.realdolmen.air.repository.TravelClassRepository;
import com.realdolmen.air.repository.TravelClassRepositoryInterface;

import javax.ejb.EJB;
import javax.enterprise.context.ApplicationScoped;
import javax.faces.bean.ManagedBean;
import java.util.List;

@ApplicationScoped
@ManagedBean
public class TravelClassServiceBean{
    @EJB
    TravelClassRepositoryInterface travelClassRepository;

    public TravelClass find(Long travelClassId){
        return travelClassRepository.find(travelClassId);
    }

    public TravelClass update(TravelClass travelClass){
        return travelClassRepository.update(travelClass);
    }

    public List<TravelClass> findAllTravelClassesOfAFlight(Long flightId,int numberOfSeats){
        return travelClassRepository.findAllTravelClassesOfAFlight(flightId,numberOfSeats);
    }
}
