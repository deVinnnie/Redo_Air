package com.realdolmen.air.beans;

import com.realdolmen.air.domain.Customer;
import com.realdolmen.air.service.CustomerServiceBean;
import org.mindrot.jbcrypt.BCrypt;

import javax.annotation.PostConstruct;
import javax.ejb.LocalBean;
import javax.faces.bean.ManagedBean;
import javax.inject.Inject;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.List;

@ManagedBean
public class RegisterBean implements Serializable {
    private Customer customer;
    @Size(min = 5, max = 20)
    private String password;
    private String repeatedPassword;

    @Inject
    CustomerServiceBean customerServiceBean;

    public RegisterBean(){
    }

    @PostConstruct
    public void postConstruct(){
        customer = new Customer();
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getRepeatedPassword() {
        return repeatedPassword;
    }

    public void setRepeatedPassword(String repeatedPassword) {
        this.repeatedPassword = repeatedPassword;
    }

    private void hashPassword(){
        String hashed = BCrypt.hashpw(password,BCrypt.gensalt());
        customer.setPassword(hashed);
    }

    public String persistCustomer(){
        if(!checkIfExists(customer.getEmail())){
            hashPassword();
            customerServiceBean.createCustomer(customer);
            return "/redo-public/searchFlight.jsf";
        }
        return "";
    }

    private boolean checkIfExists(String email){
        List<Customer> customerByEmail = customerServiceBean.findCustomerByEmail(email);
        if(0 == customerByEmail.size())
            return false;
        return true;
    }
}
