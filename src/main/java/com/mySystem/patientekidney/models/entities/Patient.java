package com.mySystem.patientekidney.models.entities;

import com.mySystem.patientekidney.librery.Prevision;
import com.mySystem.patientekidney.librery.StatePatient;
import jakarta.persistence.*;

@Entity
@Table(name = "patients")
@PrimaryKeyJoinColumn(referencedColumnName = "user_id")
public class Patient extends User {

     /*
    private String country;
    private String region;
    private String city;
    private List<Meeting> meetings;
    */
    private Prevision prevision;
    @Column(name = "state_patient")
    private StatePatient statePatient;

    @OneToOne(mappedBy = "patient",cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private Record record;

    public Patient() {
    }

    public Patient(String rut, String name, Boolean enabled, Prevision prevision, StatePatient statePatient) {
        super(rut, name, enabled);
        this.prevision = prevision;
        this.statePatient = statePatient;
    }

    public Patient(String rut, String name, Boolean enabled, Prevision prevision, StatePatient statePatient, Record record) {
        super(rut, name, enabled);
        this.prevision = prevision;
        this.statePatient = statePatient;
        this.record = record;
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

    public StatePatient getStatePatient() {
        return statePatient;
    }

    public void setStatePatient(StatePatient statePatient) {
        this.statePatient = statePatient;
    }
}
