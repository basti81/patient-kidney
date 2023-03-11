package com.mySystem.patientekidney.models.entities;

import jakarta.persistence.*;

@Entity
@Table(name = "exams")
public class Exam {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Double creatine;
    private Double albumin;
    private Double ma;
    private Double clCr;
    private Double rac;
    private Double chlorine;
    private Double potassium;
    private Double sodium;
}
