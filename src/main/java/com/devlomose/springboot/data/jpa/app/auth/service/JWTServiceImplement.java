package com.devlomose.springboot.data.jpa.app.auth.service;

import com.devlomose.springboot.data.jpa.app.auth.handler.SimpleGrantedAuthorityMixin;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.security.Key;
import java.util.Arrays;
import java.util.Collection;
import java.util.Date;

/**
 * JWTServiceImplement at: src/main/java/com/devlomose/springboot/data/jpa/app/auth/service
 * Created by @DevLomoSE at 29/9/21 21:17.
 */
@Component
public class JWTServiceImplement implements JWTService{

    public static final Key SECRET_KEY = Keys.secretKeyFor(SignatureAlgorithm.HS512);
    public static final Date EXPIRATION_DATE = new Date(System.currentTimeMillis() + 1800000);
    public static final String PREFIX = "Bearer ";
    public static final String HEADER_STRING = "Authorization";
    public static final String ROLE_STRING = "authorities";

    @Override
    public String create(Authentication auth) throws JsonProcessingException {
        String username = ((User) auth.getPrincipal()).getUsername();

        Collection<? extends GrantedAuthority> authorities = auth.getAuthorities();

        Claims claims = Jwts.claims();
        claims.put(ROLE_STRING, new ObjectMapper().writeValueAsString(authorities));

        return Jwts.builder()
                .setClaims(claims)
                .setSubject( username )
                .signWith(SECRET_KEY)
                .setIssuedAt(new Date())
                .setExpiration(EXPIRATION_DATE)
                .compact();
    }

    @Override
    public boolean validate(String token) {
        try {
            getClaims(token);

            return true;
        } catch (JwtException | IllegalArgumentException e){
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public Claims getClaims(String token) {
        return Jwts.parserBuilder()
                .setSigningKey(SECRET_KEY)
                .build()
                .parseClaimsJws(resolve(token))
                .getBody();
    }

    @Override
    public String getUsername(String token) {
        return getClaims(token).getSubject();

    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities(String token) throws IOException {
        Object roles = getClaims(token).get(ROLE_STRING);
        return Arrays.asList(new ObjectMapper()
                                        .addMixIn(SimpleGrantedAuthority.class, SimpleGrantedAuthorityMixin.class)
                                        .readValue(roles.toString().getBytes(),
                                                SimpleGrantedAuthority[].class));
    }

    @Override
    public String resolve(String token) {
        if(token != null && token.startsWith(PREFIX)){
            return token.replace(PREFIX, "");
        }
        return null;
    }
}
