package com.devlomose.springboot.data.jpa.app.models.dao;

import com.devlomose.springboot.data.jpa.app.models.entity.User;
import org.springframework.data.repository.CrudRepository;

/**
 * UsuarioDAO at: src/main/java/com/devlomose/springboot/data/jpa/app/models/dao
 * Created by @DevLomoSE at 28/9/21 11:33.
 */
public interface UserDAO extends CrudRepository<User, Long> {

    public User findByUsername(String username);

}
