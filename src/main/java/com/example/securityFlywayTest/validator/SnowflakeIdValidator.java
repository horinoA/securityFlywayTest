package com.example.securityFlywayTest.validator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.PropertyResolver;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class SnowflakeIdValidator implements ConstraintValidator<ValidSnowflakeId, Long> {

    private final PropertyResolver propertyResolver;

    @Autowired
    public SnowflakeIdValidator(PropertyResolver propertyResolver){
        this.propertyResolver = propertyResolver;
    }

    private boolean isValidSnowflakeId(long id) {
        if (id <= 0) return false;

        long epoch = propertyResolver.getProperty("snowflake.epoch", long.class);
        long nodeIdBits = propertyResolver.getProperty("snowflake.node_id_bits", long.class);
        long sequennceBits = propertyResolver.getProperty("snowflake.sequence_bits", long.class);
        long timeStamnpShift = sequennceBits + nodeIdBits;

        long timestampPart = (id >> timeStamnpShift);
        long timestamp = timestampPart + epoch;

        long now = System.currentTimeMillis();

        return timestamp >= epoch && timestamp <= now;
    }

    @Override
    public boolean isValid(Long value, ConstraintValidatorContext context) {
        return value != null && isValidSnowflakeId(value);
    }
}

