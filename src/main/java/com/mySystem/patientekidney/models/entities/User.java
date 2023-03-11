package com.mySystem.patientekidney.models.entities;

import jakarta.persistence.*;

import java.io.Serializable;
import java.time.Instant;

@Entity
@Table(name = "users")
@Inheritance(strategy = InheritanceType.JOINED)
public class User implements Serializable {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name= "user_id")
    private Long id;
    private String rut;
    private String name;
   /* @Column(name="last_name")
    private String lastName;
    private String mail;
    private String password;
    private String img;
    @Column(name = "start_date")*/
    private Instant startDate;
    private Boolean enabled;

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

    public Instant getStartDate() {
        return startDate;
    }

    public void setStartDate(Instant startDate) {
        this.startDate = startDate;
    }

    public Boolean getEnabled() {
        return enabled;
    }

    public void setEnabled(Boolean enabled) {
        this.enabled = enabled;
    }
}
