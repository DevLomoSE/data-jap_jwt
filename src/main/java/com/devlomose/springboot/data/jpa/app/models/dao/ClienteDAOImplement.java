package com.devlomose.springboot.data.jpa.app.models.dao;

import com.devlomose.springboot.data.jpa.app.models.entity.Cliente;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

/**
 * ClienteDAOImplement at: src/main/java/com/devlomose/springboot/data/jpa/app/models/dao
 * Created by @DevLomoSE at 14/9/21 10:39.
 */
@Repository
public class ClienteDAOImplement implements ClienteDAO{

    @PersistenceContext
    private EntityManager em;

    @SuppressWarnings("unchecked")
    @Transactional(readOnly = true)
    @Override
    public List<Cliente> findAll() {
        return em.createQuery("from Cliente").getResultList();
    }
}
