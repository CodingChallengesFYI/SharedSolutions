package com.tinyurl.api.userservice.validations;

import com.tinyurl.api.userservice.model.LoginRequestModel;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import org.springframework.validation.beanvalidation.CustomValidatorBean;

public class EmailUsernameValidator implements ConstraintValidator<EmailOrUsername, LoginRequestModel> {
    @Override
    public void initialize(EmailOrUsername constraintAnnotation) {
        ConstraintValidator.super.initialize(constraintAnnotation);
    }

    @Override
    public boolean isValid(LoginRequestModel loginRequest, ConstraintValidatorContext context) {
        boolean emailProvided = loginRequest.getEmail() != null && !loginRequest.getEmail().isEmpty();
        boolean usernameProvided = loginRequest.getEmail() != null && !loginRequest.getEmail().isEmpty();

        // Return true if either email or username is provided but not both
        return (emailProvided ^ usernameProvided); // XOR: one or the other, but not both
    }
}
