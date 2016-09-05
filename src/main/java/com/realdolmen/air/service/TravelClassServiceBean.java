package com.realdolmen.air.service;

import com.realdolmen.air.domain.AirlineCompany;
import com.realdolmen.air.domain.TravelClass;
import com.realdolmen.air.repository.AirlineCompanyRepository;
import com.realdolmen.air.repository.TravelClassRepository;

import javax.ejb.EJB;
import javax.enterprise.context.ApplicationScoped;
import javax.faces.bean.ManagedBean;
import java.util.List;

@ApplicationScoped
@ManagedBean
public class TravelClassServiceBean{
    @EJB
    TravelClassRepository travelClassRepository;

    public List<TravelClass> findAllTravelClassesOfAFlight(Long flightId,int numberOfSeats){
        return travelClassRepository.findAllTravelClassesOfAFlight(flightId,numberOfSeats);
    }
}
