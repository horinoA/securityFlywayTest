package com.example.securityFlywayTest;

import java.util.Locale;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.example.securityFlywayTest.service.IdService;
import com.example.securityFlywayTest.service.MessageService;

@SpringBootTest
class utilTest {

    @Autowired
    IdService idService;
    @Autowired
    MessageService messageService;
    
    @Test
    void IdServiceTest(){
        System.out.println(idService.generateId());
    }

    @Test
    void getMessegeService(){
        System.out.println(messageService.getinvalidnodeid(~(-1L << 10l)));
        System.out.println(messageService.getMessageSource().getMessage("error.retrogradelockid",null, Locale.JAPAN));
    }
}
