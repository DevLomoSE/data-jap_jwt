package com.devlomose.springboot.data.jpa.app.config;

import com.devlomose.springboot.data.jpa.app.handler.LoginSuccessHandler;
import com.devlomose.springboot.data.jpa.app.models.service.JPAUserDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import javax.sql.DataSource;

/**
 * SpringSecurityConfig at: src/main/java/com/devlomose/springboot/data/jpa/app/config
 * Created by @DevLomoSE at 27/9/21 17:04.
 */
@EnableGlobalMethodSecurity(securedEnabled = true)
@Configuration
public class SpringSecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private LoginSuccessHandler successHandler;

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    @Autowired
    private DataSource dataSource;

    @Autowired
    private JPAUserDetailService jpaUserDetailService;

    @Autowired
    public void configurerGlobal(AuthenticationManagerBuilder managerBuilder) throws Exception{
        /*
        PasswordEncoder passwordEncoder = this.passwordEncoder;
        UserBuilder userBuilder = User.builder().passwordEncoder(passwordEncoder::encode); //=> password -> passwordEncoder.encode(password)


        managerBuilder.inMemoryAuthentication()
                        .withUser(userBuilder.username("admin").password("sudo").roles("ADMIN","USER"))
                        .withUser(userBuilder.username("lomo").password("password").roles("USER"));
         */
        /*
        managerBuilder.jdbcAuthentication()
                        .dataSource(dataSource)
                        .passwordEncoder(passwordEncoder)
                        .usersByUsernameQuery("select username, password, enabled from users where username=?")
                        .authoritiesByUsernameQuery("select u.username, r.role from roles r " +
                                "inner join users u " +
                                "on (r.user_id = u.id)"+
                                "where u.username = ?");

         */
        managerBuilder.userDetailsService(jpaUserDetailService)
                        .passwordEncoder(passwordEncoder);
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {

        http.authorizeRequests()
                .antMatchers("/", "/css/**", "/js/**", "/uploads/**", "/cliente/listado", "/locale").permitAll()
                .anyRequest().authenticated()
                /*.and()
                .formLogin().successHandler(successHandler).loginPage("/auth/login").permitAll()
                .and()
                .logout().permitAll()
                .and()
                .exceptionHandling().accessDeniedPage("/error/403")*/
                .and().csrf().disable()
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
    }
}
