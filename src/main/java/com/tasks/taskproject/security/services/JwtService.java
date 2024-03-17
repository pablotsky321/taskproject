package com.tasks.taskproject.security.services;

import java.util.Date;

import javax.crypto.SecretKey;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import com.tasks.taskproject.security.entities.UserEntity;

import java.util.function.Function;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;

@Service
public class JwtService {
    
    private final String SECRET_KEY = "aeb5032f54f624668781bbc8584e5daf7a56b85732144d2a3fdaa4d9c7f65b94";

    public String extractUsername(String token){
        return extractClaim(token, Claims::getSubject);
    }

    public boolean isValid(String token, UserDetails user){
        String username = extractUsername(token);
        return (username.equals(user.getUsername())) && !isTokenExpired(token);
    }

    private boolean isTokenExpired(String token){
        return extractExpiration(token).before(new Date());
    }
    
    private Date extractExpiration(String token){
        return extractClaim(token,Claims::getExpiration);
    }

    public <T> T extractClaim(String token,Function<Claims,T> resolver){
        Claims claim = extractAllClaims(token);
        return resolver.apply(claim);
    }

    private Claims extractAllClaims(String token){
        return Jwts
                .parser()
                .verifyWith(signWithKey())
                .build()
                .parseSignedClaims(token)
                .getPayload();
    }
    
    public String generateToken(UserEntity user){
        String token = Jwts
                        .builder()
                        .subject(user.getUsername())
                        .issuedAt(new Date(System.currentTimeMillis()))
                        .expiration(new Date(System.currentTimeMillis()+24*60*60*1000))
                        .signWith(signWithKey())
                        .compact();
        
        return token;

    }

    private SecretKey signWithKey(){
        byte[] keyBytes = Decoders.BASE64URL.decode(SECRET_KEY);
        return Keys.hmacShaKeyFor(keyBytes);
    }

}
