package com.taller.auth.security;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.crypto.SecretKey;
import java.time.Instant;
import java.util.Date;
import java.util.Map;

@Service
public class JwtService {
    @Value("${app.jwt.secret}")
    private String secret;
    @Value("${app.jwt.expiration}")
    private long expiration;

    public String generateToken(Long userId, String email, String role) {
        Instant now = Instant.now();
        return Jwts.builder()
                .subject(email)
                .claims(Map.of("role", role, "userId", userId))
                .issuedAt(Date.from(now))
                .expiration(Date.from(now.plusMillis(expiration)))
                .signWith(getKey())
                .compact();
    }

    public String extractEmail(String token) {
        return Jwts.parser().verifyWith(getKey()).build().parseSignedClaims(token).getPayload().getSubject();
    }

    public String extractRole(String token) {
        return Jwts.parser().verifyWith(getKey()).build().parseSignedClaims(token).getPayload().get("role", String.class);
    }

    private SecretKey getKey() {
        return Keys.hmacShaKeyFor(Decoders.BASE64.decode(secret));
    }
}
