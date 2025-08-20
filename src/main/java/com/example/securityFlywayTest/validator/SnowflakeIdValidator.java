package com.example.securityFlywayTest.validator;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class SnowflakeIdValidator implements ConstraintValidator<ValidSnowflakeId, String> {
    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        if (value == null || value.isBlank()) {
            return false;
        }
        // 数値かつ17桁を有効と仮定
        return value.matches("\\d{17}");
    }
}