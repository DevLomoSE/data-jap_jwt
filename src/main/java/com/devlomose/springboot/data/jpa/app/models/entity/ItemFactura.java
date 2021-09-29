package com.devlomose.springboot.data.jpa.app.models.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

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

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "producto_id")
    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
    private Producto producto;

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

    public Producto getProducto() {
        return producto;
    }

    public void setProducto(Producto producto) {
        this.producto = producto;
    }

    public Double calculateImport(){
        return cantidad.doubleValue() * producto.getPrecio();
    }
}
