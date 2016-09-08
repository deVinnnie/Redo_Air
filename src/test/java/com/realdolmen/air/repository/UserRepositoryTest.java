package com.realdolmen.air.repository;

import com.realdolmen.air.domain.User;
import com.realdolmen.testutil.TestData;
import com.realdolmen.testutil.TestDataLocation;
import org.junit.Ignore;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.assertEquals;

/**
 * Created by knockaert on 5/04/2016.
 */
public class UserRepositoryTest extends AbstractRepositoryTest<UserRepository> {
    @Test
    @TestData(dataSet = TestDataLocation.USER)
    public void findByNonExistingEmailShouldReturnNoUser() {
        List<User> user = getRepository().findUserByEmail("Vincent is de beste");
        assertEquals(0, user.size());
    }
}
