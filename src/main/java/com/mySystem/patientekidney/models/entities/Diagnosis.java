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
    private Double fg;
    @ElementCollection
    @CollectionTable(name="diagnosis_state", joinColumns=@JoinColumn(name="diagnosis_id"))
    @Column(name="set_state_exam")
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

    public Double getFg() {
        return fg;
    }

    public void setFg(Double fg) {
        this.fg = fg;
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
                ", fg=" + fg +
                ", stateExamSet=" + stateExamSet +
                ", description='" + description + '\'' +
                ", diagnosisDate=" + diagnosisDate +
                ", viewed=" + viewed +
                '}';
    }
}
