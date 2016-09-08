package com.realdolmen.air.beans;

import com.realdolmen.air.domain.Customer;
import com.realdolmen.air.service.CustomerServiceBean;
import org.mindrot.jbcrypt.BCrypt;

import javax.annotation.PostConstruct;
import javax.ejb.LocalBean;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.List;

@ManagedBean
public class RegisterBean implements Serializable {
    private Customer customer;

    @Size(min = 5, max = 20)
    private String password;

    @Size(min = 5, max = 20)
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
        if(password.trim().length()>=5)
            this.password = password.trim();
        else{
            FacesContext.getCurrentInstance().addMessage("registerForm:confirmPassword", new FacesMessage("Password to short"));
        }

    }

    public String getRepeatedPassword() {
        return repeatedPassword;
    }

    public void setRepeatedPassword(String repeatedPassword) {
        this.repeatedPassword = repeatedPassword;
    }

    public String createCustomer(){
        Customer customer = customerServiceBean.createCustomer(this.customer, password);
        if(customer != null){
            return "site-index";
        }
        return "#";
    }
}
