package com.example.commentservice.util;

import com.example.commentservice.entity.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;

@RequiredArgsConstructor
@Component
public class TokenDecoder {

    @Value("${jwt.secret}")
    private String secret;

    public User getUserData(HttpHeaders headers) {
        String bearerToken = headers.getFirst(HttpHeaders.AUTHORIZATION);
        String token = bearerToken.replace("Bearer ", "");

        Claims claims = Jwts.parser()
                .setSigningKey(secret.getBytes())
                .parseClaimsJws(token)
                .getBody();

        return User.builder()
                .id(Long.valueOf(claims.get("id").toString()))
                .email(claims.get("email").toString())
                .name(claims.get("name").toString())
                .secondName(claims.get("secondName").toString())
                .build();
    }
}

