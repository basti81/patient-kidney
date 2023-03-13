package com.mySystem.patientekidney.services.interfaces;

import com.mySystem.patientekidney.models.entities.Patient;

import java.util.List;
import java.util.Optional;

public interface PatientService {
    List<Patient> findAllPatient();
    Patient savePatient (Patient patient);
    Optional<Patient> getPatientById(Long idPatient);
    void deletePatientById(Long idPatient);
    List<Patient> saveAllPatient(List<Patient> patients);
    void deleteAllPatient();
}
