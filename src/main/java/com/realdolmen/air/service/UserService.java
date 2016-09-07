package com.realdolmen.air.service;

import com.realdolmen.air.domain.User;

import javax.ejb.Remote;

@Remote
public interface UserService {
    User findUserByEmail(String email);
}
