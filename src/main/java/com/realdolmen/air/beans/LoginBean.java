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
public class LoginBean implements Serializable {
    private Customer customer;
    private String password;

    @Inject
    CustomerServiceBean customerServiceBean;

    public LoginBean(){
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

    private void checkPassword(Customer c){
        if(BCrypt.checkpw(password,c.getPassword())){
            System.out.println("sweet");
        }else{
            System.out.println("zuur");
        }
    }

    public void login(){
        Customer customerByEmail = customerServiceBean.findCustomerByEmail(customer.getEmail());
        if(customerByEmail != null){
            checkPassword(customerByEmail);
        }else {

        }
    }
}
