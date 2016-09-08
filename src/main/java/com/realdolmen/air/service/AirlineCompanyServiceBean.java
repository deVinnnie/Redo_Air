package com.realdolmen.air.service;

import com.realdolmen.air.domain.AirlineCompany;
import com.realdolmen.air.domain.Airport;
import com.realdolmen.air.domain.Customer;
import com.realdolmen.air.repository.AirlineCompanyRepository;
import com.realdolmen.air.repository.AirlineCompanyRepositoryInterface;
import com.realdolmen.air.repository.CustomerRepository;

import javax.ejb.EJB;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.enterprise.context.ApplicationScoped;
import javax.faces.bean.ManagedBean;
import java.util.List;

@Stateless
@LocalBean
public class AirlineCompanyServiceBean implements AirlineCompanyService{
    @EJB
    AirlineCompanyRepositoryInterface airlineCompanyRepository;

    @Override
    public List<AirlineCompany> getAllAirlineCompanies(){
        return airlineCompanyRepository.findAll();
    }

    @Override
    public AirlineCompany findById(Long id){
        return airlineCompanyRepository.findById(id);
    }

    @Override
    public List<AirlineCompany> findAirlineCompaniesSearch(String searchTerm){
        return airlineCompanyRepository.findAirlineCompaniesSearch(searchTerm);
    }

    @Override
    public void toggleAvailability(Long airlineCompanyId) throws InvalidIdExeption {
        AirlineCompany airlineCompany = airlineCompanyRepository.findById(airlineCompanyId);

        if(airlineCompany == null){
            throw new InvalidIdExeption("error.airport.doesnotexist");
        }
        airlineCompany.setAvailable(!airlineCompany.getAvailable());
    }

    public List<AirlineCompany> findAllActive(){
        return airlineCompanyRepository.findAllActive();
    }
}
