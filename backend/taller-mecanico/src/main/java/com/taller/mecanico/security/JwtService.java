package com.taller.mecanico.security;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.crypto.SecretKey;

@Service
public class JwtService {
    @Value("${app.jwt.secret}")
    private String secret;

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
