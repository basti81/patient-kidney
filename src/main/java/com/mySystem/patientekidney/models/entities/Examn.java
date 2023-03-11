package com.mySystem.patientekidney.models.entities;

import jakarta.persistence.*;

@Entity
@Table(name = "examns")
public class Examn {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
}
