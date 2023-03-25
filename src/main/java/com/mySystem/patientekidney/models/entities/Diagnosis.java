package com.mySystem.patientekidney.models.entities;

import com.mySystem.patientekidney.librery.StateExam;
import jakarta.persistence.*;

import java.time.Instant;
import java.util.Set;

@Entity
@Table(name = "diagnosis")
public class Diagnosis {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long idDiagnosis;

    @Column(name = "fg_cock_croft")
    private Double fgCockCroft;
    @Column(name = "fg_mdrd")
    private Double fgMdrd;
    @Column(name = "fg_ckd_epi")
    private Double fgCkdEpi;
    @ElementCollection
    @CollectionTable(name="diagnosis_states", joinColumns=@JoinColumn(name="diagnosis_id"))
    @Column(name="state_exam_set")
    private Set<StateExam> stateExamSet;
    @Column(name = "description",columnDefinition="TEXT")
    private String description;
    @Column(name = "diagnosis_date")
    private Instant diagnosisDate;
    @OneToOne
    @JoinColumn(name = "exam_id", nullable = true)
    private Exam exam;
    private boolean viewed;

    public Long getIdDiagnosis() {
        return idDiagnosis;
    }

    public void setIdDiagnosis(Long idDiagnosis) {
        this.idDiagnosis = idDiagnosis;
    }

    public Double getFgCockCroft() {
        return fgCockCroft;
    }

    public void setFgCockCroft(Double fgCockCroft) {
        this.fgCockCroft = fgCockCroft;
    }

    public Double getFgMdrd() {
        return fgMdrd;
    }

    public void setFgMdrd(Double fgMdrd) {
        this.fgMdrd = fgMdrd;
    }

    public Double getFgCkdEpi() {
        return fgCkdEpi;
    }

    public void setFgCkdEpi(Double fgCkdEpi) {
        this.fgCkdEpi = fgCkdEpi;
    }

    public Set<StateExam> getStateExamSet() {
        return stateExamSet;
    }

    public void setStateExamSet(Set<StateExam> stateExamSet) {
        this.stateExamSet = stateExamSet;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Instant getDiagnosisDate() {
        return diagnosisDate;
    }

    public void setDiagnosisDate(Instant diagnosisDate) {
        this.diagnosisDate = diagnosisDate;
    }

    public boolean isViewed() {
        return viewed;
    }

    public void setViewed(boolean viewed) {
        this.viewed = viewed;
    }

    public Exam getExam() {
        return exam;
    }

    public void setExam(Exam exam) {
        this.exam = exam;
    }

    @Override
    public String toString() {
        return "Diagnosis{" +
                "idDiagnosis=" + idDiagnosis +
                ", fgCockCroft=" + fgCockCroft +
                ", fgMdrd=" + fgMdrd +
                ", fgCkdEpi=" + fgCkdEpi +
                ", stateExamSet=" + stateExamSet +
                ", description='" + description + '\'' +
                ", diagnosisDate=" + diagnosisDate +
                ", exam=" + exam +
                ", viewed=" + viewed +
                '}';
    }
}
