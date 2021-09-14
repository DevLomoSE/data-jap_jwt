package com.devlomose.springboot.data.jpa.app.models.dao;

import com.devlomose.springboot.data.jpa.app.models.entity.Cliente;

import java.util.List;

/**
 * ClienteDAO at: src/main/java/com/devlomose/springboot/data/jpa/app/models/dao
 * Created by @DevLomoSE at 14/9/21 10:38.
 */
public interface ClienteDAO {

    public List<Cliente> findAll();

    public void save(Cliente cliente);
}
