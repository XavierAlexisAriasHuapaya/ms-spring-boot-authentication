package dev.arias.huapaya.ms_authentication.service.security;

import java.util.Date;
import java.util.Map;

import javax.crypto.SecretKey;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;

@Service
public class JwtService {

    public String generateToken(UserDetails userDetails, Map<String, Object> extraClaims) {
        String jwt = "";
        Integer minute = 1;
        Date issuedAt = new Date(System.currentTimeMillis());
        Date expired = new Date((minute * 60 * 1000) + issuedAt.getTime());
        jwt = Jwts.builder()
                .header()
                .type("JWT")
                .and()
                .subject(userDetails.getUsername())
                .issuedAt(issuedAt)
                .expiration(expired)
                .claims(extraClaims)
                .signWith(this.generateKey(), Jwts.SIG.HS256)
                .compact();
        return jwt;
    }

    private SecretKey generateKey() {
        String secret_key = "secrey_key_application_authentication_microservices";
        byte[] password = secret_key.getBytes();
        return Keys.hmacShaKeyFor(password);
    }

    private Claims extractAllClaims(String jwt) {
        return Jwts.parser().verifyWith(this.generateKey()).build().parseSignedClaims(jwt).getPayload();
    }

    public String extractUsername(String token) {
        return this.extractAllClaims(token).getSubject();
    }

}
