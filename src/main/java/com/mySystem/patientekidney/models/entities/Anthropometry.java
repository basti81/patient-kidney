package com.mySystem.patientekidney.models.entities;

import com.mySystem.patientekidney.librery.StateImc;
import jakarta.persistence.*;

import java.time.Instant;

@Entity
@Table(name = "anthropometries")
public class Anthropometry {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idAnthropometry;
    private Double height;
    private Double mass;
    private Double imc;
    @Column(name= "state_imc")
    private StateImc stateImc;
    @Column(name = "anthropometry_date")
    private Instant anthropometryDate;

    @ManyToOne
    @JoinColumn(name = "record_id", nullable = true)
    private Record record;
    public Anthropometry() {
    }

    public Long getIdAnthropometry() {
        return idAnthropometry;
    }

    public void setIdAnthropometry(Long idAnthropometry) {
        this.idAnthropometry = idAnthropometry;
    }

    public Double getHeight() {
        return height;
    }

    public void setHeight(Double height) {
        this.height = height;
    }

    public Double getMass() {
        return mass;
    }

    public void setMass(Double mass) {
        this.mass = mass;
    }

    public Double getImc() {
        return imc;
    }

    public void setImc(Double imc) {
        this.imc = imc;
    }

    public StateImc getStateImc() {
        return stateImc;
    }

    public void setStateImc(StateImc stateImc) {
        this.stateImc = stateImc;
    }

    public Instant getAnthropometryDate() {
        return anthropometryDate;
    }

    public void setAnthropometryDate(Instant anthropometryDate) {
        this.anthropometryDate = anthropometryDate;
    }

    public Record getRecord() {
        return record;
    }

    public void setRecord(Record record) {
        this.record = record;
    }
}
