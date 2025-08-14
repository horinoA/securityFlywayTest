package com.example.securityFlywayTest;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.validation.Validator;
import org.springframework.validation.beanvalidation.LocalValidatorFactoryBean;


@SpringBootApplication
public class SecurityFlywayTestApplication {

	@Bean
    public Validator validator(LocalValidatorFactoryBean factory) {
        return factory;
    }
	public static void main(String[] args) {
		SpringApplication.run(SecurityFlywayTestApplication.class, args);
	}

}
