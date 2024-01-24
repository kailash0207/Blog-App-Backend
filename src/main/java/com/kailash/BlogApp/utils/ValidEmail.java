package com.kailash.BlogApp.utils;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.*;

import static com.kailash.BlogApp.utils.Constants.ErrorMessages.INVALID_EMAIL_ID;

@Documented
@Constraint(validatedBy = CustomEmailValidator.class)
@Target({ElementType.FIELD, ElementType.METHOD, ElementType.PARAMETER})
@Retention(RetentionPolicy.RUNTIME)
public @interface ValidEmail {
    String message() default INVALID_EMAIL_ID;

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
