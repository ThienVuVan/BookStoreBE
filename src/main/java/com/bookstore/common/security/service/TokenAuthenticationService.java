package com.bookstore.common.security.service;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.security.core.Authentication;

import java.util.Date;
import java.util.List;

public class TokenAuthenticationService {
    private final long EXPIRATIONTIME = 1000 * 60 * 60 * 24 * 10; // 10 days;
    private final long REFRESHEXPIRATIONTIME = 1000 * 60 * 60 * 24 * 20; // 20 days;
    private final String secret = "123";	// Must be an environment variable into OS;
    private final String tokenPrefix = "Bearer "; // "Bearer ";
    private final String headerString = "Authorization";
    public List<String> generateToken(String username) {
        byte[] secretKeyBytes = secret.getBytes(); // Have to convert to Byte;
        String token = Jwts.builder()
                .setSubject(username)
                //.setClaims(claims) // claim is a Map;
                //.claim("authorities", authorities) // Set claim by key-value;
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + EXPIRATIONTIME))
                .signWith(SignatureAlgorithm.HS256,secretKeyBytes)
                .compact();
        String refreshToken = Jwts.builder()
                .setSubject(username)
                //.setClaims(claims)
                //.claim("authorities", authorities)
                .setExpiration(new Date(System.currentTimeMillis() + REFRESHEXPIRATIONTIME))
                .signWith(SignatureAlgorithm.HS256,secretKeyBytes)
                .compact();
        return List.of(token, refreshToken);
    }

    public Authentication authenticateRequestByToken(HttpServletRequest request) {
        byte[] secretKeyBytes = secret.getBytes();
        // need check isExist Token and  isStart with 'Bearer ' before get token (implement later);
        String token = request.getHeader(headerString).substring(7);
        String username = Jwts.parser().setSigningKey(secretKeyBytes).parseClaimsJws(token)
                .getBody().getSubject();
        return new AuthenticatedUserImpl(username);
    }
}
