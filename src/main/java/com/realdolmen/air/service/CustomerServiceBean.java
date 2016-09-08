package com.realdolmen.air.service;

import com.realdolmen.air.domain.Customer;
import com.realdolmen.air.repository.CustomerRepositoryInterface;
import org.mindrot.jbcrypt.BCrypt;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import java.util.List;

@Stateless
@LocalBean
public class CustomerServiceBean {
    @EJB
    CustomerRepositoryInterface customerRepository;

    public Customer createCustomer(Customer customer, String password){
        if(!checkIfCustomerExists(customer.getEmail())) {
            String hashed = BCrypt.hashpw(password,BCrypt.gensalt());
            customer.setPassword(hashed);
            return customerRepository.createCustomer(customer);
        }
        return null;
    }

    public List<Customer> findCustomerByEmail(String email){
        return customerRepository.findCustomerByEmail(email);
    }

    /**
     * Returns true when a customer with the given emailaddress already exists.
     *
     * @param email Emailaddress to be checked.
     * @return True if exists, false otherwise.
     */
    private boolean checkIfCustomerExists(String email){
        List<Customer> customerByEmail = this.findCustomerByEmail(email);
        if(0 == customerByEmail.size())
            return false;
        return true;
    }
}
