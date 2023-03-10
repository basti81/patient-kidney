package com.mySystem.patientekidney.services.interfaces;

import com.mySystem.patientekidney.models.entities.Patiente;

import java.util.Optional;

public interface PatienteService {
    Patiente savePatiente (Patiente patiente);
    Optional<Patiente> getPatienteById(Long idPaciente);
    void deletePatienteById(Long idPaciente);
}
