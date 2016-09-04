package com.realdolmen.air.security;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class BCryptDatabaseServerLoginModuleTest {

    BCryptDatabaseServerLoginModule loginModule;

    @Before
    public void setUp(){
        loginModule = new BCryptDatabaseServerLoginModule();
    }

    @Test
    public void test_validatePassword_WithCorrectPasswordAndHash_ReturnsTrue(){
        String password = "abba";
        String hashedPassword = "$2a$10$z5vr/uy/47FSkbtRjE4J2.Y/0MkeldTPKXoHvrK8FGYc..qcRlOAi";
        assertTrue(loginModule.validatePassword(password, hashedPassword));
    }

    @Test
    public void test_validatePassword_WithNonMathcingPasswordAndHash_ReturnsFalse(){
        String password = "password";
        String hashedPassword = "$2a$10$z5vr/uy/47FSkbtRjE4J2.Y/0MkeldTPKXoHvrK8FGYc..qcRlOAi"; //Hash for 'abba'
        assertTrue(loginModule.validatePassword(password, hashedPassword));
    }

    @Test
    public void test_validatePassword_WithPasswordEqualToNull_ReturnsFalse(){
        String password = null;
        String hashedPassword = "$2a$10$z5vr/uy/47FSkbtRjE4J2.Y/0MkeldTPKXoHvrK8FGYc..qcRlOAi";
        assertFalse(loginModule.validatePassword(password, hashedPassword));
    }

    @Test
    public void test_validatePassword_WithHashedPasswordEqualToNull_ReturnsFalse(){
        String password = "abba";
        String hashedPassword = null;
        assertFalse(loginModule.validatePassword(password, hashedPassword));
    }

    @Test
    public void test_validatePassword_WithPasswordAndHashedPasswordEqualToNull_ReturnsFalse(){
        String password = null;
        String hashedPassword = null;
        assertFalse(loginModule.validatePassword(password, hashedPassword));
    }

    @Test
    public void test_validatePassword_WithPasswordEqualToHashedPassword_ReturnsFalse(){
        String password = "abba";
        String hashedPassword = "abba";
        assertFalse(loginModule.validatePassword(password, hashedPassword));
    }

    @Test
    public void test_validatePassword_WithPasswordEmpty_ReturnsFalse(){
        String password = "";
        String hashedPassword = "$2a$10$z5vr/uy/47FSkbtRjE4J2.Y/0MkeldTPKXoHvrK8FGYc..qcRlOAi";
        assertFalse(loginModule.validatePassword(password, hashedPassword));
    }

    @Test
    public void test_validatePassword_WithHashedPasswordEmpty_ReturnsFalse(){
        String password = "abba";
        String hashedPassword = "";
        assertFalse(loginModule.validatePassword(password, hashedPassword));
    }

    @Test
    public void test_validatePassword_WithPasswordAndHashedPasswordEmpty_ReturnsFalse(){
        String password = "";
        String hashedPassword = "";
        assertFalse(loginModule.validatePassword(password, hashedPassword));
    }
}
