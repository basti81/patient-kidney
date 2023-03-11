package com.mySystem.patientekidney.models.entities;

import jakarta.persistence.*;

import java.time.LocalDate;

@Entity
@Table(name = "records")
public class Record {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name="birth_date")
    private LocalDate birthDate;
    @JoinColumn(name="patiente_id")
    @OneToOne
    private Patiente patiente;


    /*
    private List<Examn> examns;
    private List<Anthropometry> anthropometrys;
    private List<VitalSign> vitalSigns;
    private Antecedent antecedent;
    */

    public Record(LocalDate birthDate) {
        this.birthDate = birthDate;
    }

    public LocalDate getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(LocalDate birthDate) {
        this.birthDate = birthDate;
    }

    public Patiente getPatiente() {
        return patiente;
    }

    public void setPatiente(Patiente patiente) {
        this.patiente = patiente;
    }
}
