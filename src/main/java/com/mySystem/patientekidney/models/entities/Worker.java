package com.mySystem.patientekidney.models.entities;

import com.mySystem.patientekidney.librery.Specialty;
import jakarta.persistence.*;


import java.util.List;

@Entity
@Table(name = "workers")
@PrimaryKeyJoinColumn(name = "user_id")
public class Worker  extends User {

    private Specialty specialty;
    @OneToMany(mappedBy = "worker", cascade = CascadeType.ALL,fetch = FetchType.LAZY)
    private List<Meeting> meeting;

    public Worker() {

    }

    public Worker(String rut, String name, String lastName, String mail, Boolean enabled, Specialty specialty) {
        super(rut, name, lastName, mail, enabled);
        this.specialty = specialty;
    }

    public Specialty getSpecialty() {
        return specialty;
    }

    public void setSpecialty(Specialty specialty) {
        this.specialty = specialty;
    }

    public List<Meeting> getMeeting() {
        return meeting;
    }

    public void setMeeting(List<Meeting> meeting) {
        this.meeting = meeting;
    }
}
