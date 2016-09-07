package com.realdolmen.air.service;

import com.realdolmen.air.domain.User;
import com.realdolmen.air.repository.UserRepositoryInterface;

import javax.ejb.EJB;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import java.util.List;

@Stateless
@LocalBean
public class UserServiceBean implements UserService{
    @EJB
    UserRepositoryInterface userRepository;

    public User findUserByEmail(String email){
        List<User> users = userRepository.findUserByEmail(email);
        User user = null;
        if(users.isEmpty()){
            user = users.get(0);
        }
        return user;
    }
}
