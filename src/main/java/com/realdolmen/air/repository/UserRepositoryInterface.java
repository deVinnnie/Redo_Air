package com.realdolmen.air.repository;

import com.realdolmen.air.domain.User;

import java.util.List;

public interface UserRepositoryInterface {

    List<User> findUserByEmail(String email);
}
