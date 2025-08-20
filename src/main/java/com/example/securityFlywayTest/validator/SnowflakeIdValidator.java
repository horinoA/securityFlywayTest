package com.example.securityFlywayTest.validator;

import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

import jakarta.annotation.PostConstruct;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

@Component
public class SnowflakeIdValidator implements ConstraintValidator<ValidSnowflakeId, Long> {

    private final Environment environment;
    private long epoch;
    private long nodeIdBits;
    private long sequenceBits;
    private long timestampShift;
    
    public SnowflakeIdValidator(Environment environment) {
        this.environment = environment;
    }

    @PostConstruct
    public void init() {
        this.epoch = Long.parseLong(environment.getProperty("snowflake.epoch", "1749123637176"));
        this.nodeIdBits = Long.parseLong(environment.getProperty("snowflake.node_id_bits", "10"));
        this.sequenceBits = Long.parseLong(environment.getProperty("snowflake.sequence_bits","12"));
        this.timestampShift = this.nodeIdBits + this.sequenceBits;
    }

    @Override
    public boolean isValid(Long value, ConstraintValidatorContext context) {
        if (value == 0L || value == null) {
            return false;
        }
        // 数値かつ17桁を有効と仮定
        //String valueString = Long.toString(value);
        //return valueString.matches("\\d{17}");
        long timestampPart = (value >> this.timestampShift);
        long timestamp = timestampPart + this.epoch;
        long now = System.currentTimeMillis();

        return timestamp >= this.epoch && timestamp <= now;
    }
}