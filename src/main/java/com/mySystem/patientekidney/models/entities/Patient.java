package com.mySystem.patientekidney.models.entities;

import com.mySystem.patientekidney.librery.StatePrevision;
import com.mySystem.patientekidney.librery.StatePatient;
import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "patients")
@PrimaryKeyJoinColumn(referencedColumnName = "user_id")
public class Patient extends User {
    private String nationality;
    private String region;
    private String city;
    private String address;
    @OneToMany(mappedBy = "patient", cascade = CascadeType.ALL , fetch = FetchType.LAZY)
    private List<Meeting> meetings;

    @Enumerated(value = EnumType.ORDINAL)
    private StatePrevision statePrevision;
    @Column(name = "state_patient")
    private StatePatient statePatient;

    @OneToOne(mappedBy = "patient",cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private Record record;

    public Patient() { }

    public Patient(String rut, String name, String lastName, String mail, boolean enabled, StatePrevision statePrevision, StatePatient statePatient) {
        this(rut, name, lastName, mail, enabled, statePrevision, statePatient, null);
    }

    public Patient(String rut, String name, String lastName, String mail, boolean enabled, StatePrevision statePrevision, StatePatient statePatient, Record record) {
        super(rut, name, lastName, mail, enabled);
        this.statePrevision = statePrevision;
        this.statePatient = statePatient;
        this.record = record;
    }

    public StatePrevision getStatePrevision() {
        return statePrevision;
    }

    public void setStatePrevision(StatePrevision statePrevision) {
        this.statePrevision = statePrevision;
    }

    public List<Meeting> getMeetings() {
        return meetings;
    }

    public void setMeetings(List<Meeting> meetings) {
        this.meetings = meetings;
    }

    public StatePrevision getPrevision() {
        return statePrevision;
    }

    public void setPrevision(StatePrevision statePrevision) {
        this.statePrevision = statePrevision;
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

    public String getNationality() {
        return nationality;
    }

    public void setNationality(String nationality) {
        this.nationality = nationality;
    }

    public String getRegion() {
        return region;
    }

    public void setRegion(String region) {
        this.region = region;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    @Override
    public String toString() {
        return "Patient{" +
                "statePrevision=" + statePrevision +
                ", statePatient=" + statePatient +
                ", record=" + record +
                "} " + super.toString();
    }
}
