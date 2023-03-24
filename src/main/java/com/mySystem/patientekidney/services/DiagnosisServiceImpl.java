package com.mySystem.patientekidney.services;

import com.mySystem.patientekidney.models.entities.Diagnosis;
import com.mySystem.patientekidney.models.repositories.DiagnosisRepository;
import com.mySystem.patientekidney.services.interfaces.DiagnosisService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class DiagnosisServiceImpl implements DiagnosisService {

    @Autowired
    private DiagnosisRepository diagnosisRepository;

    public DiagnosisServiceImpl(DiagnosisRepository diagnosisRepository){
        this.diagnosisRepository = diagnosisRepository;
    }
    @Override
    public List<Diagnosis> findAllByIdRecord(Long id) {
        return null;
    }

    @Override
    public Boolean existsById(Long id) {
        return null;
    }

    @Override
    public List<Diagnosis> findAllDiagnosis() {
        return null;
    }

    @Override
    public Diagnosis saveDiagnosis(Diagnosis diagnosis) {
        return diagnosisRepository.save(diagnosis);
    }

    @Override
    public Optional<Diagnosis> getDiagnosisById(Long idDiagnosis) {
        return Optional.empty();
    }

    @Override
    public void deleteDiagnosisById(Long idDiagnosis) {
        diagnosisRepository.deleteById(idDiagnosis);
    }

    @Override
    public void deleteAllDiagnosis() {

    }

    @Override
    public List<Diagnosis> saveAllDiagnosis(List<Diagnosis> diagnosis) {
        return null;
    }
}
