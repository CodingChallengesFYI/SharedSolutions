package com.tinyurl.api.userservice.config;

import com.tinyurl.api.userservice.model.UserDTO;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;


import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import java.time.temporal.ChronoUnit;
import java.util.Base64;
import java.util.Date;
import java.util.concurrent.TimeUnit;

import static java.time.Instant.now;

@Component
public class JwtUtil {

    @Value("${jwt.token}")
    private String secretToken;

    public String generateToken(UserDTO userDetails) {

        byte[] secretTokenBytes = secretToken.getBytes();
        SecretKey secretKey = Keys.hmacShaKeyFor(secretTokenBytes);

        return Jwts.builder()
                .subject(userDetails.getUserId())
                .expiration(Date.from(now().plus(1, ChronoUnit.HOURS)))
                .issuedAt(Date.from(now()))
                .signWith(secretKey, Jwts.SIG.HS256)
                .compact();

    }


}
