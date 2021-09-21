package com.devlomose.springboot.data.jpa.app.models.service;

import com.devlomose.springboot.data.jpa.app.models.dao.ClienteDAO;
import com.devlomose.springboot.data.jpa.app.models.entity.Cliente;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * ClienteServiceImplement at: src/main/java/com/devlomose/springboot/data/jpa/app/models/service
 * Created by @DevLomoSE at 21/9/21 10:57.
 */
@Service
public class ClienteServiceImplement implements ClienteService {

    @Autowired
    private ClienteDAO clienteDAO;

    @Override
    @Transactional(readOnly = true)
    public List<Cliente> findAll() {
        return (List<Cliente>) clienteDAO.findAll();
    }

    @Override
    @Transactional
    public void save(Cliente cliente) {
        clienteDAO.save(cliente);
    }

    @Override
    @Transactional(readOnly = true)
    public Cliente findById(Long id) {
        return clienteDAO.findById(id).orElse(null);
    }

    @Override
    @Transactional
    public void delete(Long id) {
        clienteDAO.deleteById(id);
    }
}
