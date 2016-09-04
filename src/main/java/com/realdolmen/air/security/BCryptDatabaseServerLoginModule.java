package com.realdolmen.air.security;

import org.jboss.security.auth.spi.DatabaseServerLoginModule;
import org.mindrot.jbcrypt.BCrypt;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Custom login module to check passwords with BCrypt.
 */
public class BCryptDatabaseServerLoginModule extends DatabaseServerLoginModule {

    private Logger logger = LoggerFactory.getLogger(getClass());

    @Override
    protected boolean validatePassword(String enteredPassword, String encrypted) {
        if (enteredPassword == null || encrypted == null) {
            return false;
        }

        boolean isCorrect = false;

        try{
            isCorrect = BCrypt.checkpw(enteredPassword, encrypted);
        }
        catch(Exception e){
            logger.error(e.getMessage());
        }

        if(isCorrect) {
            logger.info("Correct login attempt");
        }
        else {
            logger.warn("Invalid login attempt!");
        }
        return isCorrect;
    }
}

