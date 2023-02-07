package com.courseology.auth;

import org.springframework.context.annotation.Configuration;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

import java.util.Date;

@Configuration
public class AuthController {
    protected final long EXPIRATION_TIME = 864_000_000; // 10 Days
    private final String SECRET = "6351665468576D5A7134743777217A25432A462D4A614E645267556B586E3272357538782F413F4428472B4B6250655368566D59713373367639792442264529";
    private final String TOKEN_PREFIX = "Bearer ";

    public String generateToken(String email) {
        String jwt = Jwts.builder()
                .setSubject(email).setExpiration(new Date(System.currentTimeMillis() + EXPIRATION_TIME)).signWith(SignatureAlgorithm.HS256, SECRET).compact();

        return TOKEN_PREFIX + jwt;
    }
}
