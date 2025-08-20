package com.example.securityFlywayTest;
import static org.assertj.core.api.Assertions.assertThat;

import java.util.Set;

import org.junit.jupiter.api.Test;

import com.example.securityFlywayTest.model.User;

import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validation;
import jakarta.validation.Validator;

class UserValidationTest {



    @Test
    void 有効なSnowflakeIdならエラーなし() {
        Validator validator = Validation.buildDefaultValidatorFactory().getValidator();
        User user = new User("24142424011968512", "test@example.com", "password");
        Set<ConstraintViolation<User>> violations = validator.validate(user);
        System.out.println(violations);
        assertThat(violations).isEmpty();
    }

    @Test
    void 無効なSnowflakeIdならエラー発生() {
        Validator validator = Validation.buildDefaultValidatorFactory().getValidator();
        User user = new User("invalid-id", "test@example.com", "password");
        Set<ConstraintViolation<User>> violations = validator.validate(user);
        assertThat(violations).isNotEmpty();
        String actualMessage = violations.iterator().next().getMessage();
        assertThat(actualMessage).isEqualTo("無効なSnowflake IDです");
    }
}
