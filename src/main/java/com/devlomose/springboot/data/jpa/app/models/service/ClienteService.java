package com.devlomose.springboot.data.jpa.app.models.service;

import com.devlomose.springboot.data.jpa.app.models.entity.Cliente;
import com.devlomose.springboot.data.jpa.app.models.entity.Producto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * ClienteService at: src/main/java/com/devlomose/springboot/data/jpa/app/models/service
 * Created by @DevLomoSE at 21/9/21 10:53.
 */
@Service
public interface ClienteService {
    public List<Cliente> findAll();

    public Page<Cliente> findAll(Pageable pageable);

    public void save(Cliente cliente);

    public Cliente findById(Long id);

    public void delete(Long id);

    public List<Producto> findByName(String nombre);
}
