package com.example.community.security;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.exceptions.TokenExpiredException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import java.util.Date;


@Component
public class JwtTokenProvider {
    @Value("${jwt.secret}")
    private String SECRET_KEY;
    private final long accessTokenValidity = 60 * 60 * 1000;  // 60분
    private final long refreshTokenValidity = 7 * 24 * 60 * 60 * 1000;  // 7일

    public String createAccessToken(String email) {
        return createToken(email, accessTokenValidity);
    }

    public String createRefreshToken(String email) {
        return createToken(email, refreshTokenValidity);
    }

    private String createToken(String email, long validityInMillis) {
        return JWT.create()
                .withSubject(email)
                .withExpiresAt(new Date(System.currentTimeMillis() + validityInMillis))
                .sign(Algorithm.HMAC256(SECRET_KEY));
    }

    public String validateToken(String token) {
        try {
            return JWT.require(Algorithm.HMAC256(SECRET_KEY))
                    .build()
                    .verify(token)
                    .getSubject();
        }  catch (TokenExpiredException e) {
            throw new RuntimeException("Token expired");
        } catch (JWTVerificationException e) {
            return null;
        }
    }
}
