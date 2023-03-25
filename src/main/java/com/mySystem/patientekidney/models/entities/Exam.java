package com.mySystem.patientekidney.models.entities;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.mySystem.patientekidney.librery.StateExam;
import jakarta.persistence.*;

import java.sql.Date;
import java.time.Instant;

@Entity
@Table(name = "exams")
public class Exam {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long idExam;
    private Double creatine;
    private Double albumin;
    private Double chlorine;
    private Double potassium;
    private Double sodium;
    private Double ma;
    @Column(name = "cl_cr")
    private Double clCr;
    private Double rac;
    @OneToOne(mappedBy = "exam",cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private Diagnosis diagnosis;
    @ManyToOne
    @JoinColumn(name = "record_id", nullable = false)
    private Record record;
    private Boolean viewed;
    @Column(name ="exam_date")
    private Instant examDate;

    public Exam() {
    }

    public Date getExamInstantToDate(){
        return Date.from(examDate);
    }
    public Boolean getViewed() {
        return viewed;
    }

    public void setViewed(Boolean viewed) {
        this.viewed = viewed;
    }

    public Long getIdExam() {
        return idExam;
    }

    public void setIdExam(Long idExam) {
        this.idExam = idExam;
    }

    public Double getCreatine() {
        return creatine;
    }

    public void setCreatine(Double creatine) {
        this.creatine = creatine;
    }

    public Double getAlbumin() {
        return albumin;
    }

    public void setAlbumin(Double albumin) {
        this.albumin = albumin;
    }

    public Double getChlorine() {
        return chlorine;
    }

    public void setChlorine(Double chlorine) {
        this.chlorine = chlorine;
    }

    public Double getPotassium() {
        return potassium;
    }

    public void setPotassium(Double potassium) {
        this.potassium = potassium;
    }

    public Double getSodium() {
        return sodium;
    }

    public void setSodium(Double sodium) {
        this.sodium = sodium;
    }

    public Double getMa() {
        return ma;
    }

    public void setMa(Double ma) {
        this.ma = ma;
    }

    public Double getClCr() {
        return clCr;
    }

    public void setClCr(Double clCr) {
        this.clCr = clCr;
    }

    public Double getRac() {
        return rac;
    }

    public void setRac(Double rac) {
        this.rac = rac;
    }

    public Instant getExamDate() {
        return examDate;
    }

    public void setExamDate(Instant examDate) {
        this.examDate = examDate;
    }

    public Record getRecord() {
        return record;
    }

    public void setRecord(Record record) {
        this.record = record;
    }

    public Diagnosis getDiagnosis() {
        return diagnosis;
    }

    public void setDiagnosis(Diagnosis diagnosis) {
        if(diagnosis == null){
            if(this.diagnosis != null){
                this.diagnosis.setExam(null);
            }
        }else{
            diagnosis.setExam(this);
        }
        this.diagnosis = diagnosis;
    }

    @Override
    public String toString() {
        return "Exam{" +
                "idExam=" + idExam +
                ", creatine=" + creatine +
                ", albumin=" + albumin +
                ", chlorine=" + chlorine +
                ", potassium=" + potassium +
                ", sodium=" + sodium +
                ", ma=" + ma +
                ", clCr=" + clCr +
                ", rac=" + rac +
                ", record=" + record +
                ", viewed=" + viewed +
                ", examDate=" + examDate +
                '}';
    }
}
