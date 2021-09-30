package com.devlomose.springboot.data.jpa.app.auth.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import io.jsonwebtoken.Claims;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;

import java.io.IOException;
import java.util.Collection;

/**
 * JWTService at: src/main/java/com/devlomose/springboot/data/jpa/app/auth/service
 * Created by @DevLomoSE at 29/9/21 21:12.
 */
public interface JWTService {

    public String create(Authentication auth) throws JsonProcessingException;

    public boolean validate(String token);

    public Claims getClaims(String token);

    public String getUsername(String token);

    public Collection<? extends GrantedAuthority> getAuthorities(String token) throws IOException;

    public String resolve(String token);

}
