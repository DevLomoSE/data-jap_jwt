package com.devlomose.springboot.data.jpa.app.models.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Cliente at: src/main/java/com/devlomose/springboot/data/jpa/app/models/entity
 * Created by @DevLomoSE at 14/9/21 10:25.
 */
@Entity
@Table(name="clientes") //<- esto para modificar el nombre de la tabla en la BD
public class Cliente implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue( strategy = GenerationType.IDENTITY )
    private Long id;

    @NotEmpty
    private String nombre;

    @NotEmpty
    private String apellido;

    @NotEmpty
    @Email
    @Column(name = "correo") //<- esto para modificar el nombre de la columna en la tabla
    private String email;

    @NotNull
    @Temporal(TemporalType.DATE)
    @Column(name="created_at",
            nullable = false)
    @DateTimeFormat(pattern = "yyyy-mm-dd")
    @JsonFormat(pattern = "yyyy-mm-dd HH:mm:ss")
    private Date createdAt;

    @Temporal(TemporalType.DATE)
    @Column(name="updated_at",
            updatable = true,
            columnDefinition="TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP")
    @JsonFormat(pattern = "yyyy-mm-dd HH:mm:ss")
    private Date updatedAt;

    private String foto;


    @OneToMany(mappedBy = "cliente",
               fetch = FetchType.LAZY,
               cascade = CascadeType.ALL)
    @JsonManagedReference
    private List<Factura> facturas;

    public Cliente() {
        this.facturas = new ArrayList<Factura>();
    }

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    public Date getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(Date updatedAt) {
        this.updatedAt = updatedAt;
    }

    public String getFoto() {
        return foto;
    }

    public void setFoto(String foto) {
        this.foto = foto;
    }

    public List<Factura> getFacturas() {
        return facturas;
    }

    public void setFacturas(List<Factura> facturas) {
        this.facturas = facturas;
    }

    public void addFactura(Factura factura){
        facturas.add(factura);
    }

    public Double getFinalTotal(){
        Double total = 0.0;
        for(int i=0; i<this.facturas.size(); i++){
            total += this.facturas.get(i).getTotal();
        }

        return total;
    }

    @Override
    public String toString() {
        return nombre+" "+apellido;
    }
}
