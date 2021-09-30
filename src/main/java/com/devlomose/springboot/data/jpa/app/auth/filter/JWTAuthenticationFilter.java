package com.devlomose.springboot.data.jpa.app.auth.filter;

import com.devlomose.springboot.data.jpa.app.auth.service.JWTService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * JWTAuthenticationFilter at: src/main/java/com/devlomose/springboot/data/jpa/app/auth/filter
 * Created by @DevLomoSE at 29/9/21 19:01.
 */
public class JWTAuthenticationFilter extends UsernamePasswordAuthenticationFilter {

    private AuthenticationManager authenticationManager;

    private JWTService jwtService;

    public JWTAuthenticationFilter(AuthenticationManager authenticationManager, JWTService jwtService) {
        this.jwtService = jwtService;
        this.authenticationManager = authenticationManager;
        setRequiresAuthenticationRequestMatcher(new AntPathRequestMatcher("/api/auth/login", "POST"));
    }

    @Override
    public Authentication attemptAuthentication(HttpServletRequest request,
                                                HttpServletResponse response) throws AuthenticationException {

        String username = obtainUsername(request);
        String password = obtainPassword(request);

        com.devlomose.springboot.data.jpa.app.models.entity.User userdata = null;

        if(username == null && password == null){
            try {
                userdata = new ObjectMapper().readValue(request.getInputStream(),
                            com.devlomose.springboot.data.jpa.app.models.entity.User.class);

                username = userdata.getUsername();
                password = userdata.getPassword();

                logger.info("user from request inputStream: "+username);
                logger.info("password from request inputStream: "+password);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }else{
            logger.info("user from request parameter: "+username);
            logger.info("password from request parameter: "+password);
        }

        username = username.trim();

        UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(username, password);

        return authenticationManager.authenticate(authToken);
    }

    @Override
    protected void successfulAuthentication(HttpServletRequest request,
                                            HttpServletResponse response,
                                            FilterChain chain,
                                            Authentication authResult) throws IOException, ServletException {

        String token = jwtService.create(authResult);

        logger.info("generated token: "+token);
        response.addHeader("Authorization", "Bearer " + token);

        Map<String, Object> body = new HashMap<String, Object>();
        body.put("token", token);
        body.put("user", (User) authResult.getPrincipal());
        body.put("message", String.format("%s, Sesión iniciada con éxito", authResult.getName()));

        response.getWriter().write(new ObjectMapper().writeValueAsString(body));
        response.setStatus(200);
        response.setContentType("application/json");
    }

    @Override
    protected void unsuccessfulAuthentication(HttpServletRequest request,
                                              HttpServletResponse response,
                                              AuthenticationException failed) throws IOException, ServletException {
        Map<String, Object> body = new HashMap<String, Object>();
        body.put("message", "Error de autenticación, credenciales invalidas");
        body.put("error", failed.getMessage());

        response.getWriter().write(new ObjectMapper().writeValueAsString(body));
        response.setStatus(401);
        response.setContentType("application/json");
    }
}
