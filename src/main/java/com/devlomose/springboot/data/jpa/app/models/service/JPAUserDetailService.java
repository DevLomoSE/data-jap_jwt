package com.devlomose.springboot.data.jpa.app.models.service;

import com.devlomose.springboot.data.jpa.app.models.dao.UserDAO;
import com.devlomose.springboot.data.jpa.app.models.entity.Role;
import com.devlomose.springboot.data.jpa.app.models.entity.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

/**
 * JPAUserDetailService at: src/main/java/com/devlomose/springboot/data/jpa/app/models/service
 * Created by @DevLomoSE at 28/9/21 11:43.
 */
@Service("JPAUserDetailService")
public class JPAUserDetailService implements UserDetailsService {

    @Autowired
    private UserDAO userDAO;

    private final Logger logger = LoggerFactory.getLogger(getClass());

    @Override
    @Transactional(readOnly = true)
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userDAO.findByUsername(username);

        if(user == null){
            logger.error("Error in login: El usuario "+username+" no existe en la BD.");
            throw new UsernameNotFoundException("Username "+username+" no esta registrado en el sistma");
        }

        List<GrantedAuthority> authorityList = new ArrayList<GrantedAuthority>();

        for(Role role: user.getRoles()){
            authorityList.add(new SimpleGrantedAuthority(role.getRole()));
        }

        if(authorityList.isEmpty()){
            logger.error("Error in login: El usuario "+username+" no tiene roles asignados");
            throw new UsernameNotFoundException("Username "+username+" no tiene roles asignados");
        }

        return new org.springframework.security.core.userdetails.User(
                user.getUsername(), user.getPassword(), user.getEnabled(),
                true, true, true,
                authorityList);
    }
}
