package com.example.securityFlywayTest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.env.Environment;

import com.example.securityFlywayTest.config.PropertyDiagnostics;
import com.example.securityFlywayTest.service.TestService;
import com.example.securityFlywayTest.validator.SnowflakeIdValidator;

@SpringBootTest
public class SnowflakeIdValidTest {
    
    @Autowired
    SnowflakeIdValidator snowflakeIdValidator;

    @Autowired
    private Environment environment;

    @Autowired
    private TestService testServic;

    @Autowired
    PropertyDiagnostics prop;

    @Test
    void snowflakeIdValidatorIntelizeTest(){
        long value = snowflakeIdValidator.getEpoch();
        assertNotNull(value, "Property value for '" + value + "' should not be null.");
        
    }

    @Test
    void property_should_be_loaded_correctly() {
        // application.propertiesに定義したキー
        String propertyKey = "snowflake.epoch";
        // application.propertiesに定義した期待値
        String expectedValue = "1749123637176";

        // Environmentオブジェクトがnullでないことを確認（前回のテストと同じ）
        assertNotNull(environment);

        // プロパティの値を取得
        String propertyValue = environment.getProperty(propertyKey);
        System.out.println(propertyValue);
        // 値がnullでなく、期待値と一致することを確認
        assertNotNull(propertyValue, "Property value for '" + propertyKey + "' should not be null.");
        assertEquals(expectedValue, propertyValue, "Property value does not match the expected value.");
    }

    @Test
    void testServictest(){
        testServic.testEpoch();
    }
}
