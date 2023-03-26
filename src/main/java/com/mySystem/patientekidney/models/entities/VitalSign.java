package com.mySystem.patientekidney.models.entities;

import jakarta.persistence.*;

import java.time.*;
import java.util.Date;

@Entity
@Table(name = "vital_signs")
public class VitalSign {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long idVitalSign;
    @Column(name = "p_high")
    private Integer pressureHigh;
    @Column(name = "p_low")
    private Integer pressureLow;
    private Integer breathing;
    private Double temperature;
    private Integer pulse;
    private Double saturation;
    @Column(name = "vital_sign_date")
    private Instant vitalSignDate;
    private Boolean viewed;

    @ManyToOne
    @JoinColumn(name = "record_id", nullable = false)
    private Record record;



    public VitalSign() {
    }

    public Long getIdVitalSign() {
        return idVitalSign;
    }

    public void setIdVitalSign(Long idVitalSign) {
        this.idVitalSign = idVitalSign;
    }

    public Integer getPressureHigh() {
        return pressureHigh;
    }

    public void setPressureHigh(Integer pressureHigh) {
        this.pressureHigh = pressureHigh;
    }

    public Integer getPressureLow() {
        return pressureLow;
    }

    public void setPressureLow(Integer pressureLow) {
        this.pressureLow = pressureLow;
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

    public Date getVitalSignInstantToDate(){
        return Date.from(vitalSignDate);
    }

    public LocalDate getVitalSignInstantToLocalDate(){
        return LocalDate.ofInstant(vitalSignDate,ZoneOffset.UTC);
    }
    public LocalDateTime getVitalSignInstantToLocalDateTime(){
        return LocalDateTime.ofInstant(vitalSignDate, ZoneOffset.UTC);
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

    @Override
    public String toString() {
        return "VitalSign{" +
                "idVitalSign=" + idVitalSign +
                ", pressureHigh=" + pressureHigh +
                ", pressureLow=" + pressureLow +
                ", breathing=" + breathing +
                ", temperature=" + temperature +
                ", pulse=" + pulse +
                ", saturation=" + saturation +
                ", vitalSignDate=" + vitalSignDate +
                ", viewed=" + viewed +
                '}';
    }
}
