package com.mySystem.patientekidney.models.entities;

import jakarta.persistence.*;

import java.io.Serializable;

@Entity
@Table(name = "users")
@Inheritance(strategy = InheritanceType.JOINED)
public class User implements Serializable {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name= "user_id")
    private Long id;
    private String rut;
    private String name;

    public User() {
    }

    public User(String rut, String name) {
        this.rut = rut;
        this.name = name;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getRut() {
        return rut;
    }

    public void setRut(String rut) {
        this.rut = rut;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", rut='" + rut + '\'' +
                ", name='" + name + '\'' +
                '}';
    }
}
