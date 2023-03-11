package com.mySystem.patientekidney.models.entities;

import jakarta.persistence.*;

import java.time.LocalDate;

@Entity
@Table(name = "records")
public class Record {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "record_id")
    private Long id;
    @Column(name="birth_date")
    private LocalDate birthDate;
    @OneToOne
    @JoinColumn(name="patiente_id")
    private Patient patient;


   /*
    private List<Examn> examns;
    private List<Anthropometry> anthropometrys;
    privte List<italSign> vitalSigns;
    private Antecedent antecedent;
    */

    public Record() {
    }

    public Record(LocalDate birthDate) {
        this.birthDate = birthDate;
    }

    public LocalDate getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(LocalDate birthDate) {
        this.birthDate = birthDate;
    }

    public Patient getPatient() {
        return patient;
    }

    public void setPatient(Patient patient) {
        this.patient = patient;
    }
}
