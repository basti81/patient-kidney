package com.mySystem.patientekidney.services.interfaces;

import com.mySystem.patientekidney.models.entities.Exam;
import com.mySystem.patientekidney.models.entities.VitalSign;

import java.util.List;
import java.util.Optional;

public interface VitalSignService {

    List<VitalSign> findAllByIdRecord(Long id);
    List<VitalSign> findAllByIdVitalSign(Long id);
    Boolean existsById(Long id);
    List<VitalSign> findAllVitalSign();
    VitalSign saveVitalSign (VitalSign vitalSign);
    Optional<VitalSign> getVitalSignById(Long idVitalSign);
    void deleteVitalSignById(Long idVitalSign);
    void deleteAllVitalSign();
    List<VitalSign> saveAllVitalSign(List<VitalSign> vitalSigns);
}
