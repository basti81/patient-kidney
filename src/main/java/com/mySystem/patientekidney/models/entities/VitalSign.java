package com.mySystem.patientekidney.models.entities;

import jakarta.persistence.*;

import java.time.Instant;

@Entity
@Table(name = "vital_signs")
public class VitalSign {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long idVitalSign;
    private Integer pHigh;
    private Integer pLow;
    private Integer breathing;
    private Double temperature;
    private Integer pulse;
    private Double saturation;
    @Column(name = "vital_sign_date")
    private Instant vitalSignDate;

    @ManyToOne()
    @JoinColumn(name = "record_id", nullable = false)
    private Record record;


    public VitalSign() {
    }

    private Boolean viewed;

    public Long getIdVitalSign() {
        return idVitalSign;
    }

    public void setIdVitalSign(Long idVitalSign) {
        this.idVitalSign = idVitalSign;
    }

    public Integer getpHigh() {
        return pHigh;
    }

    public void setpHigh(Integer pHigh) {
        this.pHigh = pHigh;
    }

    public Integer getpLow() {
        return pLow;
    }

    public void setpLow(Integer pLow) {
        this.pLow = pLow;
    }

    public Integer getBreathing() {
        return breathing;
    }

    public void setBreathing(Integer breathing) {
        this.breathing = breathing;
    }

    public Double getTemperature() {
        return temperature;
    }

    public void setTemperature(Double temperature) {
        this.temperature = temperature;
    }

    public Integer getPulse() {
        return pulse;
    }

    public void setPulse(Integer pulse) {
        this.pulse = pulse;
    }

    public Double getSaturation() {
        return saturation;
    }

    public void setSaturation(Double saturation) {
        this.saturation = saturation;
    }

    public Instant getVitalSignDate() {
        return vitalSignDate;
    }

    public void setVitalSignDate(Instant vitalSignDate) {
        this.vitalSignDate = vitalSignDate;
    }

    public Boolean getViewed() {
        return viewed;
    }

    public void setViewed(Boolean viewed) {
        this.viewed = viewed;
    }

    public Record getRecord() {
        return record;
    }

    public void setRecord(Record record) {
        this.record = record;
    }
}
