package com.mySystem.patientekidney.models.entities;

import jakarta.persistence.*;

import java.time.Instant;

@Entity
@Table(name = "vitals_signs")
public class VitalSign {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Integer pHigh;
    private Integer pLow;
    private Integer breathing;
    private Double temperature;
    private Integer pulse;
    private Double saturation;
    private Instant vitalSignDate;
    /*
    @ManyToOne()
    @JoinColumn(name = "record_id", nullable = false)
    private Record record;
    */
    private Boolean viewed;
}
