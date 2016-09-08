package com.realdolmen.air.beans;

import com.realdolmen.air.domain.Customer;
import com.realdolmen.air.domain.Employee;
import com.realdolmen.air.domain.User;
import com.realdolmen.air.service.UserService;
import com.realdolmen.air.service.UserServiceBean;

import javax.annotation.PostConstruct;
import javax.enterprise.context.SessionScoped;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.security.Principal;

@Named
@SessionScoped
public class UserBean implements Serializable{

    @Inject
    private UserServiceBean userService;

    private User user;

    @PostConstruct
    public void setUp(){
        Principal userPrincipal = FacesContext.getCurrentInstance().getExternalContext().getUserPrincipal();

        if(userPrincipal != null) {
            user = userService.findUserByEmail(userPrincipal.getName());
        }
    }

    public User getUser() {
        return user;
    }

    public void setUser(Customer user) {
        this.user = user;
    }

    public Customer getUserAsCustomer(){
        if(user instanceof Customer){
            return (Customer) user;
        }
        return null;
    }

    public Employee getUserAsEmployee(){
        if(user instanceof Employee){
            return (Employee) user;
        }
        return null;
    }

    public String logout(){
        HttpServletRequest request = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();
        try {
            request.logout();
        } catch (ServletException e) {
            e.printStackTrace();
        }

        return "site-index";
    }
}
