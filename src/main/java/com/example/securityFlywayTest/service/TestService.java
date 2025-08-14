package com.example.securityFlywayTest.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.securityFlywayTest.validator.SnowflakeIdValidator;

@Service
public class TestService {

    @Autowired
    SnowflakeIdValidator validator;

    /*public TestService(SnowflakeIdValidator validator) {
        this.validator = validator;
    }*/
    
    public void testEpoch() {
        System.out.println("epoch from service = " + validator.getEpoch());
    }
}