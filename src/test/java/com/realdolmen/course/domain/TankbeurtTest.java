package com.realdolmen.course.domain;


import com.realdolmen.course.utilities.persistence.JpaPersistenceTest;
import org.junit.Before;
import org.junit.Test;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import java.util.Date;
import java.util.Set;

public class TankbeurtTest extends JpaPersistenceTest {

    private Validator validator;


    @Before
    public void setUp(){
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        this.validator = factory.getValidator();
    }

    @Test
    public void testNewTankbeurtWithMoreKilometersThanLastIsValid(){
        Tankbeurt tankbeurt = new Tankbeurt("NNN777", 10.0, 10.0, 10.0, new Date());
        Set<ConstraintViolation<Tankbeurt>> violations = validator.validate(tankbeurt);
        assertEquals(0, violations.size());
    }
}
