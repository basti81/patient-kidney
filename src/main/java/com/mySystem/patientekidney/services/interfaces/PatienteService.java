package com.mySystem.patientekidney.services.interfaces;

import com.mySystem.patientekidney.models.entities.Patiente;

import java.util.List;
import java.util.Optional;

public interface PatienteService {
    List<Patiente> listOfPatiente();
    Patiente savePatiente (Patiente patiente);
    Optional<Patiente> getPatienteById(Long idPaciente);
    void deletePatienteById(Long idPaciente);
}
