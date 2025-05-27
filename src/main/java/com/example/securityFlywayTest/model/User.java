package com.example.securityFlywayTest.model;
import org.springframework.data.relational.core.mapping.Table;

import jakarta.validation.constraints.NotEmpty;

@Table("users")
public record User(
    Long id,
    @NotEmpty(message = "Email cannot be empty")
    
    String email,
    String password,
    boolean enabled
) {} 