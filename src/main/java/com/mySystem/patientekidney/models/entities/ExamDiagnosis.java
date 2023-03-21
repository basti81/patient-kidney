package com.mySystem.patientekidney.models.entities;

import com.mySystem.patientekidney.librery.StateExam;
import jakarta.persistence.*;

import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "diagnosis")
public class ExamDiagnosis {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long idExamDiagnosis;
    private Double fg;

    @ElementCollection
    @CollectionTable(name="diagnosis_exam_state", joinColumns=@JoinColumn(name="diagnosis_exam_id"))
    @Column(name="state_exam")
    private Set<StateExam> stateExamSet;
    @Column(name = "description",columnDefinition="TEXT")
    private String description;

    private boolean viewed;
}
