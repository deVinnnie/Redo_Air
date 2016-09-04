package com.realdolmen.air.beans;

import com.realdolmen.air.domain.Customer;
import com.realdolmen.air.service.CustomerServiceBean;
import org.mindrot.jbcrypt.BCrypt;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import java.io.Serializable;
import java.security.Principal;

@ManagedBean
public class LoginBean implements Serializable {
    private Customer customer;
    private String password;

    @Inject
    CustomerServiceBean customerServiceBean;


    private boolean loginFailure = false;

    public LoginBean(){
    }

    @PostConstruct
    public void postConstruct(){
        customer = new Customer();

        Principal userPrincipal = FacesContext.getCurrentInstance().getExternalContext().getUserPrincipal();

        if(userPrincipal != null) {

            String userName = userPrincipal.getName();
            System.out.println("Username is " + userName);
        }
        else{
            System.out.println("Username not available");
        }
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

    public boolean isLoginFailure() {
        return loginFailure;
    }

    public void setLoginFailure(boolean loginFailure) {
        this.loginFailure = loginFailure;
    }

    public void login(){
        Customer customerByEmail = customerServiceBean.findCustomerByEmail(customer.getEmail());
        if(customerByEmail != null){
            checkPassword(customerByEmail);
        }else {

        }
    }

    public String logout(){
        HttpServletRequest request = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();
        try {
            request.logout();
        } catch (ServletException e) {
            e.printStackTrace();
        }

        return "/index.xhtml?faces-redirect=true";
    }
}
