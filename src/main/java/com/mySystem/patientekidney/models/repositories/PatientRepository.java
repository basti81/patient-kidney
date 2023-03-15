package com.mySystem.patientekidney.models.repositories;


import com.mySystem.patientekidney.models.entities.Patient;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PatientRepository extends JpaRepository<Patient,Long> {
    public Boolean existsByRut(String rut);
}
