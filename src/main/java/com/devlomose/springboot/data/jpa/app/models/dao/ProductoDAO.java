package com.devlomose.springboot.data.jpa.app.models.dao;

import com.devlomose.springboot.data.jpa.app.models.entity.Producto;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

/**
 * ProductoDAO at: src/main/java/com/devlomose/springboot/data/jpa/app/models/dao
 * Created by @DevLomoSE at 27/9/21 9:43.
 */
public interface ProductoDAO extends CrudRepository<Producto, Long> {

    @Query("select producto from Producto producto where producto.nombre like %?1%")
    public List<Producto> findByName(String nombre);
}
