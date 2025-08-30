package com.example.securityFlywayTest.validator;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class BCryptValidator implements ConstraintValidator<ValidBCrypt, String>{

    // BCrypt の基本的なパターン（$2a$, $2b$, $2y$ に対応）
    private static final String BCRYPT_PATTERN = "^\\$2[aby]?\\$\\d{2}\\$[./A-Za-z0-9]{53}$";

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        return value != null && value.matches(BCRYPT_PATTERN);
    }
    
}
