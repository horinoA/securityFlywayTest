package com.example.securityFlywayTest;

import java.util.Set;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import com.example.securityFlywayTest.model.User;

import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validation;

@SpringBootTest
public class UserModelTest {

    
    //24142424011968512
    @Test
    void userVaridSnowflakeTest()   {
        User user = new User(24142424011968512L, "test@exsample.com", "password");
        Set<ConstraintViolation<User>> result =
        Validation
        .buildDefaultValidatorFactory()
        .getValidator()
        .validate(user);
        System.out.println(result);
    }
    
}
