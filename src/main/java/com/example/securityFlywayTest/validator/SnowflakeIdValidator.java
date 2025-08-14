package com.example.securityFlywayTest.validator;

import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

import jakarta.annotation.PostConstruct;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

@Component
public class SnowflakeIdValidator implements ConstraintValidator<ValidSnowflakeId, Long>{

    private final Environment environment;
    private Long epoch;

    SnowflakeIdValidator(Environment environment){
        this.environment = environment;
    }

    /*applicaation.proppertiessから初期化が必要な値は 
     * @PostConstruct から取得する
     */
    @PostConstruct
    public void init() {
        String epochString = environment.getProperty("snowflake.epoch");
        this.epoch = epochString != null ? Long.parseLong(epochString) : null;
        System.out.println("Validator initialized epoch = " + epoch);
    }

    @Override
    public boolean isValid(Long value, ConstraintValidatorContext context) {
        //return value != null && value.equals(epoch);
        return value != null;
    }
    
    public Long getEpoch() {
        return epoch;
    }
}
