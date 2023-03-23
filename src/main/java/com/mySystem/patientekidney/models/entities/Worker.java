package com.mySystem.patientekidney.models.entities;

import com.mySystem.patientekidney.librery.StateSpecialty;
import jakarta.persistence.*;


import java.util.List;

@Entity
@Table(name = "workers")
@PrimaryKeyJoinColumn(name = "user_id")
public class Worker  extends User {

    private StateSpecialty stateSpecialty;
    @OneToMany(mappedBy = "worker", cascade = CascadeType.ALL,fetch = FetchType.LAZY)
    private List<Meeting> meeting;

    public Worker() {

    }

    public Worker(String rut, String name, String lastName, String mail, Boolean enabled, StateSpecialty stateSpecialty) {
        super(rut, name, lastName, mail, enabled);
        this.stateSpecialty = stateSpecialty;
    }

    public StateSpecialty getSpecialty() {
        return stateSpecialty;
    }

    public void setSpecialty(StateSpecialty stateSpecialty) {
        this.stateSpecialty = stateSpecialty;
    }

    public List<Meeting> getMeeting() {
        return meeting;
    }

    public void setMeeting(List<Meeting> meeting) {
        this.meeting = meeting;
    }

    @Override
    public String toString() {
        return "Worker{" +
                "specialty=" + stateSpecialty +
                "} " + super.toString();
    }
}
