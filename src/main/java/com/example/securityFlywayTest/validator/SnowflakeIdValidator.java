package com.example.securityFlywayTest.validator;

import com.example.securityFlywayTest.util.SnowflakeUtils;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class SnowflakeIdValidator implements ConstraintValidator<ValidSnowflakeId, Long> {

    @Override
    public boolean isValid(Long value, ConstraintValidatorContext context) {
        return value != null && SnowflakeUtils.isValidSnowflakeId(value);
    }
}

