package com.example.securityFlywayTest.util;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class SnowflakeIdValidator implements ConstraintValidator<ValidSnowflakeId, Long> {

    @Override
    public boolean isValid(Long value, ConstraintValidatorContext context) {
        return value != null && SnowflakeUtils.isValidSnowflakeId(value);
    }
}

