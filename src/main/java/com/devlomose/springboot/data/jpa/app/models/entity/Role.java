package com.devlomose.springboot.data.jpa.app.models.entity;

import javax.persistence.*;
import java.io.Serializable;

/**
 * Role at: src/main/java/com/devlomose/springboot/data/jpa/app/models/entity
 * Created by @DevLomoSE at 28/9/21 11:24.
 */
@Entity
@Table(name="roles", uniqueConstraints = {@UniqueConstraint(columnNames = {"user_id", "role"})})
public class Role implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String role;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    @Override
    public String toString() {
        return "Role{" +
                "id=" + id +
                ", role='" + role + '\'' +
                '}';
    }
}
