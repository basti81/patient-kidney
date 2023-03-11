package com.mySystem.patientekidney.models.entities;

import com.mySystem.patientekidney.librery.Prevision;
import com.mySystem.patientekidney.librery.State;
import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "patients")
@PrimaryKeyJoinColumn(referencedColumnName = "user_id")
public class Patient extends User {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="patient_id")
    private Long id;

   /*
    private String country;
    private String region;
    private String city;
    private List<Meeting> meetings;
    */
    private Prevision prevision;
    private State state;

    @OneToOne(mappedBy = "patient",cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private Record record;

    public Patient() {
    }

    public Patient(String rut, String name, Prevision prevision, State state) {
        super(rut, name);
        this.prevision = prevision;
        this.state = state;
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
        if(record == null){
            if(this.record != null){
                this.record.setPatient(null);
            }
        }else{
            record.setPatient(this);
        }
        this.record = record;
    }

    public State getState() {
        return state;
    }

    public void setState(State state) {
        this.state = state;
    }
}
