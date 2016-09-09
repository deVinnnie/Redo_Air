package com.realdolmen.air.beans;

import com.realdolmen.air.domain.Customer;
import com.realdolmen.air.domain.Employee;
import com.realdolmen.air.domain.User;
import com.realdolmen.air.repository.FlightRepository;
import com.realdolmen.air.service.UserServiceBean;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.annotation.PostConstruct;
import javax.enterprise.context.SessionScoped;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import java.io.Serializable;
import java.security.Principal;

@Named
@SessionScoped
public class UserBean implements Serializable{
    private static final Logger LOGGER = LoggerFactory.getLogger(FlightRepository.class);

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

    public void setUser(User user) {
        this.user = user;
    }

    /**
     * Tries to cast the current user to an Employee and returns the result.
     *
     * @return An Employee object, or null no user is logged in,
     *              or the user is not an employee.
     */
    public Customer getUserAsCustomer(){
        if(user instanceof Customer){
            return (Customer) user;
        }
        return null;
    }

    /**
     * Tries to cast the current user to an Employee and returns the result.
     *
     * @return An Employee object, or null no user is logged in,
     *              or the user is not an employee.
     */
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
            LOGGER.error("Servlet Exception in logout", e);
        }

        return "site-index";
    }
}
