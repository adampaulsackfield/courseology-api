package com.courseology.auth;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import org.springframework.context.annotation.Configuration;

import java.security.Key;
import java.util.Date;

@Configuration
public class AuthController {
    private final long EXPIRATION_TIME = 864_000_000; // 10 Days
    private final Key SECRET = Keys.secretKeyFor(SignatureAlgorithm.HS256);
    private final String TOKEN_PREFIX = "Bearer ";

    public String generateToken(String email) {
        String jwt = Jwts.builder()
                .setSubject(email).setExpiration(new Date(System.currentTimeMillis() + EXPIRATION_TIME)).signWith(SignatureAlgorithm.HS256, SECRET).compact();

        return TOKEN_PREFIX + jwt;
    }

    public Claims validateToken(String token) {
        try {
            Claims claims = Jwts.parserBuilder().setSigningKey(SECRET).build().parseClaimsJws(token.replace(TOKEN_PREFIX, "")).getBody();
            return claims;
        } catch (JwtException exception) {
            return null;
        }
    }
}
