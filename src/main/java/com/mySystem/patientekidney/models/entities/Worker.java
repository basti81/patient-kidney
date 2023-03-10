package com.mySystem.patientekidney.models.entities;

import jakarta.persistence.*;

@Entity
@Table(name = "workers")
public class Worker  extends User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String specialty;

    public Worker() {
    }

    public Worker(String rut, String name, String specialty) {
        super(rut, name);
        this.specialty = specialty;
    }

    @Override
    public Long getId() {
        return id;
    }

    @Override
    public void setId(Long id) {
        this.id = id;
    }

    public String getSpecialty() {
        return specialty;
    }

    public void setSpecialty(String specialty) {
        this.specialty = specialty;
    }
}
