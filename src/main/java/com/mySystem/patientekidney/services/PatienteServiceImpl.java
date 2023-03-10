package com.mySystem.patientekidney.services;


import com.mySystem.patientekidney.models.entities.Patiente;
import com.mySystem.patientekidney.models.repositories.PatienteRepository;
import com.mySystem.patientekidney.services.interfaces.PatienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.util.Optional;

@Service
public class PatienteServiceImpl implements PatienteService {
    @Autowired
    private PatienteRepository patienteRepository;
    public PatienteServiceImpl(PatienteRepository patienteRepository){
        this.patienteRepository = patienteRepository;
    }
    @Override
    public Patiente savePatiente(Patiente patiente) {
        return patienteRepository.save(patiente);
    }

    @Override
    public Optional<Patiente> getPatienteById(Long idPaciente) {
        return Optional.of(patienteRepository.getById(idPaciente));
    }

    @Override
    public void deletePatienteById(Long idPaciente) {
        patienteRepository.deleteById(idPaciente);
    }
}
