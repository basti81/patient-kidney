package com.mySystem.patientekidney.models.entities;

import com.mySystem.patientekidney.librery.StateGenre;
import com.mySystem.patientekidney.librery.StateRace;
import jakarta.persistence.*;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;
import java.time.Period;
import java.util.List;

@Entity
@Table(name = "records")
public class Record {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name ="genre")
    @Enumerated(value = EnumType.STRING)
    private StateGenre stateGenre;
    @Column(name = "ethnicity")
    @Enumerated(value = EnumType.STRING)
    private StateRace stateRace;
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

    @OneToMany(mappedBy = "record", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Anthropometry> anthropometries;

    //private Antecedent antecedent;


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

    public StateGenre getStateGenre() {
        return stateGenre;
    }

    public void setStateGenre(StateGenre stateGenre) {
        this.stateGenre = stateGenre;
    }

    public StateRace getStateRace() {
        return stateRace;
    }

    public void setStateRace(StateRace stateRace) {
        this.stateRace = stateRace;
    }

    public LocalDate getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(LocalDate birthDate) {
        this.birthDate = birthDate;
    }

    public Integer getAge() {
        return age;
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

    public List<Anthropometry> getAnthropometries() {
        return anthropometries;
    }

    public void setAnthropometries(List<Anthropometry> anthropometries) {
        this.anthropometries = anthropometries;
    }

    @Override
    public String toString() {
        return "Record{" +
                "stateGenre=" + stateGenre +
                ", stateRace=" + stateRace +
                ", birthDate=" + birthDate +
                '}';
    }
}
