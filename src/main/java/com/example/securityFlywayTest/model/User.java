package com.example.securityFlywayTest.model;
import org.springframework.data.relational.core.mapping.Table;

import com.example.securityFlywayTest.validator.ValidSnowflakeId;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;

@Table("users")
public record User(
    @ValidSnowflakeId
    Long id,
    @NotEmpty
    @Email
    String email,
    String password
) {} 