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
    private StateImc stateImc;
    @Column(name = "anthropometry_date")
    private Instant anthropometryDate;

    @ManyToOne
    @JoinColumn(name = "record_id", nullable = true)
    private Record record;
    public Anthropometry() {
    }
}
