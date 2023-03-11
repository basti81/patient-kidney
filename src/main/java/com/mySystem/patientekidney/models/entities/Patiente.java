package com.mySystem.patientekidney.models.entities;

import com.mySystem.patientekidney.librery.Prevision;
import com.mySystem.patientekidney.librery.State;
import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "patients")
@PrimaryKeyJoinColumn(referencedColumnName = "user_id")
public class Patiente extends User {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

   /*
    private String country;
    private String region;
    private String city;
    private List<Meeting> meetings;
    */
    private Prevision prevision;
    private State state;

    @OneToOne(mappedBy = "patients",cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private Record record;

    public Patiente() {
    }

    public Patiente(String rut, String name, Prevision prevision) {
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

    public Prevision getPrevision() {
        return prevision;
    }

    public void setPrevision(Prevision prevision) {
        this.prevision = prevision;
    }

    public Record getRecord() {
        return record;
    }

    public void setRecord(Record record) {
        this.record = record;
    }

    public State getState() {
        return state;
    }

    public void setState(State state) {
        this.state = state;
    }
}
