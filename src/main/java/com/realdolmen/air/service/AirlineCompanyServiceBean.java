package com.realdolmen.air.service;

import com.realdolmen.air.domain.AirlineCompany;
import com.realdolmen.air.domain.Customer;
import com.realdolmen.air.repository.AirlineCompanyRepository;
import com.realdolmen.air.repository.CustomerRepository;

import javax.ejb.EJB;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import java.util.List;

@Stateless
@LocalBean
public class AirlineCompanyServiceBean implements AirlineCompanyService{
    @EJB
    AirlineCompanyRepository airlineCompanyRepository;

    @Override
    public List<AirlineCompany> getAllAirlineCompanies(){
        return airlineCompanyRepository.findAll();
    }
}
