package com.snipper.Snipper.Snippets.util;

import io.jsonwebtoken.*;
import io.jsonwebtoken.security.Keys;

import java.security.Key;
import java.util.Date;

public class JwtUtil {
    private static final Key SECRET_KEY = Keys.secretKeyFor(SignatureAlgorithm.HS256);
    private static final long EXPIRATION_TIME = 24 * 60 * 60 * 1000; // 24 hours in milliseconds


    public static String generateToken(String email){
        return Jwts.builder()
                .setSubject(email)
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis()+ EXPIRATION_TIME))
                .signWith(SECRET_KEY)
                .compact();
    }

    public static String validateToken(String token){
        try{
            Jws<Claims> claims = Jwts.parserBuilder()
                    .setSigningKey(SECRET_KEY)
                    .build()
                    .parseClaimsJws(token);
            return claims.getBody().getSubject();
        } catch (JwtException e) {
            return null; // Invalid or expired token
        }
    }
}
