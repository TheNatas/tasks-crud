package com.example.hellobackend.util;

import io.jsonwebtoken.*;
import io.jsonwebtoken.security.Keys;
import org.springframework.stereotype.Component;

import java.security.Key;
import java.util.Date;

@Component
public class JwtUtil {
    private final Key key = Keys.secretKeyFor(SignatureAlgorithm.HS256);
    private final long expirationMs = 1000 * 60 * 15; // 15 minutes
    private final long refreshExpirationMs = 1000 * 60 * 60 * 24; // 24 hours

    public String generateToken(String username, String role) {
      return Jwts.builder()
          .setSubject(username)
          .claim("role", role)
          .setIssuedAt(new Date())
          .setExpiration(new Date(System.currentTimeMillis() + expirationMs))
          .signWith(key)
          .compact();
    }

    public String generateRefreshToken(String username, String role) {
      return Jwts.builder()
          .setSubject(username)
          .claim("role", role)
          .setIssuedAt(new Date())
          .setExpiration(new Date(System.currentTimeMillis() + refreshExpirationMs))
          .signWith(key)
          .compact();
    }
  
    public String extractRole(String token) {
      return Jwts.parserBuilder().setSigningKey(key).build()
              .parseClaimsJws(token).getBody().get("role", String.class);
    }
  

    public String extractUsername(String token) {
        return Jwts.parserBuilder().setSigningKey(key).build()
                .parseClaimsJws(token).getBody().getSubject();
    }

    public boolean validateToken(String token) {
        try {
            Jwts.parserBuilder().setSigningKey(key).build()
                    .parseClaimsJws(token);
            return true;
        } catch (JwtException e) {
            return false;
        }
    }
}
