package com.trangbn.springboot_application.annotation;

import com.trangbn.springboot_application.validations.PasswordStrengthValidator;
import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.*;

@Documented
@Constraint(validatedBy = PasswordStrengthValidator.class)
@Target({ElementType.METHOD, ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
public @interface PasswordValidator {
    String message() default "Password is not strong enough!";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
