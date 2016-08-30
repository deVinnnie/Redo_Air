package com.realdolmen.course.domain;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.FIELD;

@Target({FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = AccountNumberValidator.class)
public @interface ValidAccountNumber {
    String message() default "Not a valid Account Number";
    Class<?>[] groups() default {}; // to control the order of validation
    Class<? extends Payload>[] payload() default {};
}
