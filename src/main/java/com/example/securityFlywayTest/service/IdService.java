package com.example.securityFlywayTest.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.securityFlywayTest.util.SnowflakeIdGenerator;

@Service
public class IdService {
    
    @Autowired
    SnowflakeIdGenerator snowflakeIdGenerator;

    public Long generateId() {
        return snowflakeIdGenerator.nextId();
    }
}
