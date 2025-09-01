package com.example.securityFlywayTest.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.example.securityFlywayTest.model.User;

@Repository
public class UserRepository {
    @Autowired
    JdbcTemplate jdbcTemplate;

    public void save(User user) {
        String sql = "INSERT INTO users (id, email, password) VALUES (?, ?, ?)";
        jdbcTemplate.update(sql, user.id(), user.email(), user.password());
    }

    public void enableUser(String email) {
        String sql = "UPDATE users SET enabled = true WHERE email = ?";
        jdbcTemplate.update(sql, email);
    }

    public User findByEmail(String email) {
        String sql = "SELECT * FROM users WHERE email = ?";
        return jdbcTemplate.queryForObject(sql, new BeanPropertyRowMapper<>(User.class), email);
    }
}
