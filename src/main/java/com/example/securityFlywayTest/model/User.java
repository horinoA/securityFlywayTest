package com.example.securityFlywayTest.model;
import com.example.securityFlywayTest.validator.ValidSnowflakeId;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

public record User(
    @ValidSnowflakeId
    @NotNull
    String id,
    @NotEmpty
    @Email
    String email,
    String password

) {} 