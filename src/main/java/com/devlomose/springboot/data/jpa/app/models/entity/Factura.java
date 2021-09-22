package com.devlomose.springboot.data.jpa.app.models.entity;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

/**
 * Factura at: src/main/java/com/devlomose/springboot/data/jpa/app/models/entity
 * Created by @DevLomoSE at 22/9/21 10:46.
 */
@Entity
@Table(name="facturas")
public class Factura implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String descripcion;
    private String observacion;

    @Temporal(TemporalType.DATE)
    @Column(name = "created_at")
    private Date createdAt;

    @Temporal(TemporalType.DATE)
    @Column(name="updated_at",
            updatable = true,
            columnDefinition="TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP")
    private Date updatedAt;

    @ManyToOne(fetch = FetchType.LAZY)
    private Cliente cliente;

    private static final long serialVersionUID = 1L;

    @PrePersist
    public void prePersist(){
        this.createdAt = new Date();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getObservacion() {
        return observacion;
    }

    public void setObservacion(String observacion) {
        this.observacion = observacion;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }
}
