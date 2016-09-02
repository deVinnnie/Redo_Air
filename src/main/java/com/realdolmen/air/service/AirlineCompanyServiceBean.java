package com.realdolmen.air.service;

import com.realdolmen.air.domain.AirlineCompany;
import com.realdolmen.air.domain.Customer;
import com.realdolmen.air.repository.AirlineCompanyRepository;
import com.realdolmen.air.repository.CustomerRepository;

import javax.ejb.EJB;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.enterprise.context.ApplicationScoped;
import javax.faces.bean.ManagedBean;
import java.util.List;

@ApplicationScoped
@ManagedBean(name = "themeService")
public class AirlineCompanyServiceBean implements AirlineCompanyService{
    @EJB
    AirlineCompanyRepository airlineCompanyRepository;

    @Override
    public List<AirlineCompany> getAllAirlineCompanies(){
        return airlineCompanyRepository.findAll();
    }

    public AirlineCompany findById(Long id){
        return airlineCompanyRepository.findById(id);
    }
}
