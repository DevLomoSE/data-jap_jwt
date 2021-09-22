package com.devlomose.springboot.data.jpa.app.models.entity;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

/**
 * ItemFactura at: src/main/java/com/devlomose/springboot/data/jpa/app/models/entity
 * Created by @DevLomoSE at 22/9/21 11:04.
 */
@Entity
@Table(name="facturas_items")
public class ItemFactura implements Serializable {

     @Id
     @GeneratedValue(strategy = GenerationType.IDENTITY)
     private int id;

     private Integer cantidad;

    @Temporal(TemporalType.DATE)
    @Column(name = "created_at")
    private Date createdAt;

    //!WIP: agregar relacion con producto

    private static final long serialVersionUID = 1L;

    @PrePersist
    public void prePersist(){
        this.createdAt = new Date();
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Integer getCantidad() {
        return cantidad;
    }

    public void setCantidad(Integer cantidad) {
        this.cantidad = cantidad;
    }

    //!WIP: agregar logica matematica para obtener el total
    public Long calculateImport(){
        return cantidad.longValue();
    }
}
