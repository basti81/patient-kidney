package com.mySystem.patientekidney.services.interfaces;

import com.mySystem.patientekidney.models.entities.Patiente;

import java.util.List;
import java.util.Optional;

public interface PatienteService {
    List<Patiente> findAllPatiente();
    Patiente savePatiente (Patiente patiente);
    Optional<Patiente> getPatienteById(Long idPatiente);
    void deletePatienteById(Long idPatiente);
}
