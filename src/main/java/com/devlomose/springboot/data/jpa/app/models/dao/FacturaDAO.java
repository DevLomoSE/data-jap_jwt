package com.devlomose.springboot.data.jpa.app.models.dao;

import com.devlomose.springboot.data.jpa.app.models.entity.Factura;
import org.springframework.data.repository.CrudRepository;

/**
 * FacturaDAO at: src/main/java/com/devlomose/springboot/data/jpa/app/models/dao
 * Created by @DevLomoSE at 27/9/21 11:49.
 */
public interface FacturaDAO extends CrudRepository<Factura, Long> {

}
