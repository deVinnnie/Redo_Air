package com.realdolmen.air.repository;

import com.realdolmen.air.domain.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.ejb.Stateless;
import javax.persistence.TypedQuery;
import java.util.Collections;
import java.util.List;

@Stateless
public class UserRepository extends AbstractBaseRepository<User, Long> implements UserRepositoryInterface{
    private static final Logger LOGGER = LoggerFactory.getLogger(FlightRepository.class);

    @Override
    public List<User> findUserByEmail(String email){
        try{
            TypedQuery<User> query = getEntityManager().createNamedQuery(User.FIND_BY_EMAIL, User.class);
            query.setParameter("email",email);
            return query.getResultList();
        }catch (Exception e){
            LOGGER.error("No information found: ", e);
        }
        return Collections.emptyList();
    }
}
