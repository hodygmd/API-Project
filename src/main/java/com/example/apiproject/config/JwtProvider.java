package com.example.apiproject.config;

import com.example.apiproject.dto.DistanceUnitDto;
import com.example.apiproject.dto.UserDto;
import com.example.apiproject.entities.DistanceUnit;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import jakarta.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ResponseStatusException;

import java.io.Serializable;
import java.util.Base64;
import java.util.Date;
import java.util.Map;

@Component
public class JwtProvider implements Serializable {
    @Value("${jwt.secret}")
    private String secret;

    @PostConstruct
    public void init() {
        secret = Base64.getEncoder()
                .encodeToString(secret.getBytes());
    }

    public String createToken(UserDto user) {
        Map<String, Object> claims =
                Jwts.claims().setSubject(user.getUsername());
        claims.put("id", user.getId());
        Date now = new Date();
        Date expiration = new Date(now.getTime()+(((3600*1000)*24)*10));
        return Jwts.builder()
                .setClaims(claims)
                .setIssuedAt(now)
                .setExpiration(expiration)
                .signWith(SignatureAlgorithm.HS256,
                        secret)
                .compact();
    }

    public void validate(String token) {
        try {
            Jwts.parser()
                    .setSigningKey(secret)
                    .parseClaimsJws(token);
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED);
        }
    }

    public String getUsernameFromToken(String token) {
        try {
            return Jwts.parser()
                    .setSigningKey(secret)
                    .parseClaimsJws(token)
                    .getBody()
                    .getSubject();
        } catch (Exception e) {
            return "bad token";
        }
    }
}
