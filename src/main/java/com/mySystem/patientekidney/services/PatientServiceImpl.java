package com.mySystem.patientekidney.services;


import com.mySystem.patientekidney.models.entities.Patient;
import com.mySystem.patientekidney.models.repositories.PatientRepository;
import com.mySystem.patientekidney.services.interfaces.PatientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.util.List;
import java.util.Optional;

@Service
public class PatientServiceImpl implements PatientService {
    @Autowired
    private PatientRepository patientRepository;
    public PatientServiceImpl(PatientRepository patienteRepository){
        this.patientRepository = patientRepository;
    }


    @Override
    public List<Patient> findAllPatient() {
        return patientRepository.findAll();
    }
    @Override
    public Patient savePatient(Patient patient) {
        return patientRepository.save(patient);
    }
    @Override
    public Optional<Patient> getPatientById(Long idPatient) {
        return Optional.of(patientRepository.getById(idPatient));
    }
    @Override
    public void deletePatientById(Long idPatient) {
        patientRepository.deleteById(idPatient);
    }
    @Override
    public List<Patient> saveAllPatient(List<Patient> patients) {
        return patientRepository.saveAll(patients);
    }

    @Override
    public void deleteAllPatient() {
        patientRepository.deleteAll();
    }
}
