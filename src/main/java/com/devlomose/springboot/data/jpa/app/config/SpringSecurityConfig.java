package com.devlomose.springboot.data.jpa.app.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.User.UserBuilder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

/**
 * SpringSecurityConfig at: src/main/java/com/devlomose/springboot/data/jpa/app/config
 * Created by @DevLomoSE at 27/9/21 17:04.
 */
@Configuration
public class SpringSecurityConfig extends WebSecurityConfigurerAdapter {

    @Bean
    public BCryptPasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

    @Autowired
    public void configurerGlobal(AuthenticationManagerBuilder managerBuilder) throws Exception{
        PasswordEncoder passwordEncoder = passwordEncoder();
        UserBuilder userBuilder = User.builder().passwordEncoder(passwordEncoder::encode); //=> password -> passwordEncoder.encode(password)


        managerBuilder.inMemoryAuthentication()
                        .withUser(userBuilder.username("admin").password("sudo").roles("ADMIN","USER"))
                        .withUser(userBuilder.username("lomo").password("password").roles("USER"));
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {

        http.authorizeRequests()
                .antMatchers("/", "/css/**", "/js/**", "/uploads/**", "/cliente/listado").permitAll()
                .antMatchers("/cliente/ver/**").hasAnyRole("USER")
                .antMatchers("/cliente/uploads/**").hasAnyRole("USER")
                .antMatchers("/cliente/form/**").hasAnyRole("ADMIN")
                .antMatchers("/cliente/eliminar/**").hasAnyRole("ADMIN")
                .antMatchers("/factura/**").hasAnyRole("ADMIN")
                .anyRequest().authenticated()
                .and()
                .formLogin().permitAll()
                .and()
                .logout().permitAll();
    }
}
