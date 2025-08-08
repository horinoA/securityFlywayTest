package com.example.securityFlywayTest.util;

import java.util.Locale;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Component;

@Component
public class MessageService {

    @Autowired
    MessageSource messageSource;

    public String getinvalidnodeid(long id) {
        return messageSource.getMessage("error.invalidnodeid", new Object[]{id}, Locale.JAPAN);
    }

    public MessageSource getMessageSource(){
        return this.messageSource;
    }
    
}
