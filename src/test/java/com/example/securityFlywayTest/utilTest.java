package com.example.securityFlywayTest;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.example.securityFlywayTest.service.IdService;

@SpringBootTest
class utilTest {

    @Autowired
    IdService idService;
    
    @Test
    void IdServiceTest(){
        System.out.println(idService.generateId());
    }
}
