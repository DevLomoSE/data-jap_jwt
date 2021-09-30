package com.devlomose.springboot.data.jpa.app.auth.filter;

import com.devlomose.springboot.data.jpa.app.auth.service.JWTService;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static com.devlomose.springboot.data.jpa.app.auth.service.JWTServiceImplement.HEADER_STRING;
import static com.devlomose.springboot.data.jpa.app.auth.service.JWTServiceImplement.PREFIX;

/**
 * JWTAuthorizationFilter at: src/main/java/com/devlomose/springboot/data/jpa/app/auth/filter
 * Created by @DevLomoSE at 29/9/21 20:32.
 */
public class JWTAuthorizationFilter extends BasicAuthenticationFilter {

    private JWTService jwtService;

    public JWTAuthorizationFilter(AuthenticationManager authenticationManager, JWTService jwtService) {
        super(authenticationManager);
        this.jwtService = jwtService;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request,
                                    HttpServletResponse response,
                                    FilterChain chain) throws IOException, ServletException {

        String header = request.getHeader(HEADER_STRING);
        logger.info(header);

        if(!requiresAuthentication(header)){
            chain.doFilter(request, response);
            return;
        }

        UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = null;

        logger.info(jwtService.validate(header));
        if(jwtService.validate(header)){
            usernamePasswordAuthenticationToken = new UsernamePasswordAuthenticationToken(jwtService.getUsername(header),
                                                                    null, jwtService.getAuthorities(header));
            logger.info(header);
        }else{
            logger.info(header);
        }

        SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken);
        chain.doFilter(request, response);
    }

    protected boolean requiresAuthentication(String header){
        if(header == null || !header.startsWith(PREFIX)) {
            return false;
        }
        return true;
    }
}
