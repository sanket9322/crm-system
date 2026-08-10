package com.example.demo.security;

import java.nio.charset.StandardCharsets;
import java.security.Key;
import java.util.Date;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;

@Component
public class JwtUtils {

    private final String SECRET_KEY;

public JwtUtils(@Value("${jwt.secret}") String secretKey) {
    this.SECRET_KEY = secretKey;
}
    private Key getSignKey() {
        return Keys.hmacShaKeyFor(
                SECRET_KEY.getBytes(StandardCharsets.UTF_8)
        );
    }

    // -----------------------------------------
    // Generate JWT
    // -----------------------------------------
    public String generateToken(UserDetails userDetails) {

        String role = userDetails.getAuthorities()
                .stream()
                .findFirst()
                .map(authority -> authority.getAuthority())
                .orElse("USER");

        return Jwts.builder()
                .setSubject(userDetails.getUsername())
                .claim("role", role)
                .setIssuedAt(new Date())
                .setExpiration(
                        new Date(
                                System.currentTimeMillis()
                                        + 1000L * 60 * 60 * 10
                        )
                )
                .signWith(
                        getSignKey(),
                        SignatureAlgorithm.HS256
                )
                .compact();
    }

    // -----------------------------------------
    // Extract username
    // -----------------------------------------
    public String extractUsername(String token) {

        return extractAllClaims(token)
                .getSubject();
    }

    // -----------------------------------------
    // Extract claims
    // -----------------------------------------
    public Claims extractAllClaims(String token) {

        return Jwts.parserBuilder()
                .setSigningKey(getSignKey())
                .build()
                .parseClaimsJws(token)
                .getBody();
    }

    // -----------------------------------------
    // Validate token
    // -----------------------------------------
    public boolean isTokenValid(
            String token,
            UserDetails userDetails) {

        try {

            String username = extractUsername(token);

            return username.equals(userDetails.getUsername())
                    && !isTokenExpired(token);

        } catch (Exception e) {

            System.out.println(
                    "TOKEN VALIDATION ERROR: "
                            + e.getMessage()
            );

            return false;
        }
    }

    // -----------------------------------------
    // Check expiration
    // -----------------------------------------
    private boolean isTokenExpired(String token) {

        return extractAllClaims(token)
                .getExpiration()
                .before(new Date());
    }
}