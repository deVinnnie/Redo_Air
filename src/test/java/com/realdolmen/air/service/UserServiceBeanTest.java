package com.realdolmen.air.service;

import com.realdolmen.air.domain.Booking;
import com.realdolmen.air.domain.Employee;
import com.realdolmen.air.domain.User;
import com.realdolmen.air.repository.BookingRepository;
import com.realdolmen.air.repository.UserRepository;
import com.realdolmen.air.repository.UserRepositoryInterface;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class UserServiceBeanTest {

    @Mock
    private UserRepositoryInterface userRepositoryInterface;

    @InjectMocks
    private UserServiceBean userServiceBean;

    User user;

    List<User> users;

    @Before
    public void setupMox() {
        user = new Employee();
        user.setId(1L);

        users = new ArrayList<>();
        users.add(user);


        when(userRepositoryInterface.findUserByEmail("email")).thenReturn(users);
    }

    @Test
    public void test_getUserReturnsCorrectUser(){
        User result = userServiceBean.findUserByEmail("email");

        assertNotNull(result);
        Long expected = Long.parseLong("1");
        assertEquals(expected,result.getId());

        verify(userRepositoryInterface).findUserByEmail("email");
        verifyNoMoreInteractions(userRepositoryInterface);
    }
}
