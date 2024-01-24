package com.kailash.BlogApp.utils;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import org.apache.commons.validator.routines.EmailValidator;

public class CustomEmailValidator implements ConstraintValidator<ValidEmail, String> {

    private static final EmailValidator emailValidator = EmailValidator.getInstance();

    @Override
    public void initialize(ValidEmail constraintAnnotation) {
    }

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        return value != null && emailValidator.isValid(value);
    }
}

