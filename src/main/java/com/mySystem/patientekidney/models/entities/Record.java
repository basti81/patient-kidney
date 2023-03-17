package com.mySystem.patientekidney.models.entities;

import jakarta.persistence.*;
import org.springframework.format.annotation.DateTimeFormat;

import java.text.DateFormat;
import java.text.Format;
import java.time.Instant;
import java.time.LocalDate;
import java.time.Period;
import java.util.List;

@Entity
@Table(name = "records")
public class Record {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name="birth_date")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate birthDate;
    @Transient
    private Integer age;
    @OneToOne
    @JoinColumn(name="patient_id")
    private Patient patient;

    @OneToMany(mappedBy = "record", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Exam> exams;

    @OneToMany(mappedBy = "record", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<VitalSign> vitalSigns;

   /* private List<Anthropometry> anthropometrys;
    privte List<italSign> vitalSigns;
    private Antecedent antecedent;
    */

    public Record() {
    }

    public Record(LocalDate birthDate) {
        this.birthDate = birthDate;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getAgeInteger() {
        return Period.between(birthDate,LocalDate.now()).getYears();
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public String getAgeString(){
        Period period = Period.between(birthDate,LocalDate.now());
        return period.getYears()+" years, "+period.getMonths()+" month, " +period.getDays()+" days";
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

    public List<Exam> getExams() {
        return exams;
    }

    public void setExams(List<Exam> exams) {
        this.exams = exams;
    }

    public List<VitalSign> getVitalSigns() {
        return vitalSigns;
    }

    public void setVitalSigns(List<VitalSign> vitalSigns) {
        this.vitalSigns = vitalSigns;
    }
}
