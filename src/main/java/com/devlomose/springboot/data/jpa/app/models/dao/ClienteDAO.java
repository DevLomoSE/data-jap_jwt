package com.devlomose.springboot.data.jpa.app.models.dao;

import com.devlomose.springboot.data.jpa.app.models.entity.Cliente;
import org.springframework.data.repository.PagingAndSortingRepository;

/**
 * ClienteDAO at: src/main/java/com/devlomose/springboot/data/jpa/app/models/dao
 * Created by @DevLomoSE at 14/9/21 10:38.
 */

public interface ClienteDAO extends PagingAndSortingRepository<Cliente, Long> {

}
