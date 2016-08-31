package com.realdolmen.air.beans;

import com.realdolmen.air.domain.Customer;
import com.realdolmen.air.service.CustomerServiceBean;
import org.mindrot.jbcrypt.BCrypt;

import javax.annotation.PostConstruct;
import javax.ejb.LocalBean;
import javax.faces.bean.ManagedBean;
import javax.inject.Inject;
import java.io.Serializable;

@LocalBean
@ManagedBean
public class RegisterBean implements Serializable {
    private Customer customer;
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

    public void persistCustomer(){
        hashPassword();
        customerServiceBean.createCustomer(customer);
    }
}
