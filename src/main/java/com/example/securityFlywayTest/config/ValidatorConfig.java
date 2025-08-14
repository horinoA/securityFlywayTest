package com.example.securityFlywayTest.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.validation.beanvalidation.LocalValidatorFactoryBean;

/*ConstraintValidator を Spring Bean 化する
@Component を付けて、さらに LocalValidatorFactoryBean に SpringConstraintValidatorFactory を設定する 
起動ファイル内の
@Bean
    public Validator validator
にてcallしている
*/
@Configuration
public class ValidatorConfig {
    @Bean
    public LocalValidatorFactoryBean validatorFactoryBean() {
        return new LocalValidatorFactoryBean();
    }

}
