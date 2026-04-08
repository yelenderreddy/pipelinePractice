package com.employee.management.security;

import java.nio.charset.StandardCharsets;
import java.util.Date;

import org.springframework.stereotype.Component;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;

@Component
public class JwtUtil {

    private static final String SECRET_KEY =
            "employee_secret_key_employee_secret_key_12345"; // min 32 chars

    private static final long EXPIRATION_TIME = 1000 * 60 * 60; // 1 hour

    public String generateToken(String empId) {
        return Jwts.builder()
                .setSubject(empId)
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + EXPIRATION_TIME))
                .signWith(
                    Keys.hmacShaKeyFor(SECRET_KEY.getBytes(StandardCharsets.UTF_8))
                )
                .compact();
    }

    public String extractEmpId(String token) {
        return Jwts.parserBuilder()
                .setSigningKey(
                    Keys.hmacShaKeyFor(SECRET_KEY.getBytes(StandardCharsets.UTF_8))
                )
                .build()
                .parseClaimsJws(token)
                .getBody()
                .getSubject();
    }
}
