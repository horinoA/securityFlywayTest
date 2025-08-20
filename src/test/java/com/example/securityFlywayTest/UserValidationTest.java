package com.example.securityFlywayTest;
import static org.assertj.core.api.Assertions.assertThat;

import java.util.Set;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.example.securityFlywayTest.model.User;

import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validator;

@SpringBootTest
class UserValidationTest {
    @Autowired
     Validator validator;

    @Test
    void 有効なSnowflakeIdならエラーなし() {
        //Validator validator = Validation.buildDefaultValidatorFactory().getValidator();
        User user = new User(24142424011968512L, "test@example.com", "password");
        Set<ConstraintViolation<User>> violations = validator.validate(user);
        System.out.println(violations);
        assertThat(violations).isEmpty();
    }

    @Test
    void 無効なSnowflakeIdならエラー発生() {
        User user = new User(0l, "test@example.com", "password");
        Set<ConstraintViolation<User>> violations = validator.validate(user);
        assertThat(violations).isNotEmpty();
        String actualMessage = violations.iterator().next().getMessage();
        assertThat(actualMessage).isEqualTo("無効なSnowflake IDです");
    }
}
