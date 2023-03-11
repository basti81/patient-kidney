package com.mySystem.patientekidney.models.entities;

import com.mySystem.patientekidney.librery.Specialty;
import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "workers")
@PrimaryKeyJoinColumn(name = "user_id")
public class Worker  extends User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /*@OneToMany(mappedBy = "workers", cascade = CascadeType.ALL,fetch = FetchType.LAZY)
    private List<Meeting> meeting; */
    private Specialty specialty;

    public Worker() {
    }

    public Worker(String rut, String name, Specialty specialty) {
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

    public Specialty getSpecialty() {
        return specialty;
    }

    public void setSpecialty(Specialty specialty) {
        this.specialty = specialty;
    }
}
