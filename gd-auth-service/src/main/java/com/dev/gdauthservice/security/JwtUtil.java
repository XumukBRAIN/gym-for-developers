package com.dev.gdauthservice.security;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.dev.gdauthservice.models.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.Date;

import static com.dev.gdauthservice.security.SecurityConstants.TOKEN_PREFIX;

@Component
public class JwtUtil {

    @Value("${jwt.expiration-time}")
    private Long expirationTime;

    @Autowired
    public Algorithm algorithm;

    public Date makeExpirationDate() {
        return new Date(System.currentTimeMillis() + expirationTime);
    }

    public String generateToken(User user) {
        return JWT.create()
                .withIssuer("XumukBRAIN")
                .withIssuedAt(new Date())
                .withExpiresAt(makeExpirationDate())
                .withSubject(user.getName())
                .withClaim("id", user.getId())
                .withExpiresAt(makeExpirationDate())
                .sign(algorithm);
    }

    public String getSubjectFromToken(String token) {
        return JWT.require(algorithm)
                .build()
                .verify(token.replace(TOKEN_PREFIX, ""))
                .getSubject();
    }
}
