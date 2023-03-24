package com.mySystem.patientekidney.services.interfaces;

import com.mySystem.patientekidney.models.entities.Diagnosis;
import com.mySystem.patientekidney.models.entities.Exam;

import java.util.List;
import java.util.Optional;

public interface DiagnosisService {
    List<Diagnosis> findAllByIdRecord(Long id);
    Boolean existsById(Long id);
    List<Diagnosis> findAllDiagnosis();
    Diagnosis saveDiagnosis (Diagnosis diagnosis);
    Optional<Diagnosis> getDiagnosisById(Long idDiagnosis);
    void deleteDiagnosisById(Long idDiagnosis);
    void deleteAllDiagnosis();
    List<Diagnosis> saveAllDiagnosis(List<Diagnosis> diagnosis);
}
