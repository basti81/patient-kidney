package com.mySystem.patientekidney.models.entities;

import jakarta.persistence.*;

@Entity
@Table(name = "patients")
@PrimaryKeyJoinColumn(referencedColumnName = "user_id")
public class Patiente extends User {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String prevision;

    public Patiente() {
    }

    public Patiente(String rut, String name, String prevision) {
        super(rut, name);
        this.prevision = prevision;
    }

    @Override
    public Long getId() {
        return id;
    }

    @Override
    public void setId(Long id) {
        this.id = id;
    }

    public String getPrevision() {
        return prevision;
    }

    public void setPrevision(String prevision) {
        this.prevision = prevision;
    }

    @Override
    public String toString() {
        return "Patiente{" +
                "id=" + id +
                ", prevision='" + prevision + '\'' +
                "} " + super.toString();
    }
}
