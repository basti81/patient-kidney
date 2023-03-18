package com.mySystem.patientekidney.services.interfaces;

import com.mySystem.patientekidney.models.entities.Anthropometry;
import com.mySystem.patientekidney.models.entities.Exam;

import java.util.List;
import java.util.Optional;

public interface AnthropometryService {
    List<Anthropometry> findAllByIdRecord(Long id);
    Boolean existsById(Long id);
    List<Anthropometry> findAllAnthropometry();
    Anthropometry saveAnthropometry (Anthropometry anthropometry);
    Optional<Anthropometry> getAnthropometryById(Long idAnthropometry);
    void deleteAnthropometryById(Long idAnthropometry);
    void deleteAllAnthropometry();
    List<Anthropometry> saveAllAnthropometry(List<Anthropometry> anthropometries);
}
