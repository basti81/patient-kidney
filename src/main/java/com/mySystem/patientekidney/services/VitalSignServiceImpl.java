package com.mySystem.patientekidney.services;

import com.mySystem.patientekidney.models.entities.Exam;
import com.mySystem.patientekidney.models.entities.VitalSign;
import com.mySystem.patientekidney.models.repositories.VitalSignRepository;
import com.mySystem.patientekidney.services.interfaces.VitalSignService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class VitalSignServiceImpl implements VitalSignService {

    @Autowired
    private VitalSignRepository vitalSignRepository;

    public VitalSignServiceImpl(VitalSignRepository vitalSignRepository) {
        this.vitalSignRepository = vitalSignRepository;
    }


    @Override
    public List<VitalSign> findAllByIdRecord(Long id) {
        return vitalSignRepository.findAllVitalSignByIdRecord(id);
    }


    @Override
    public Boolean existsById(Long id) {
        return vitalSignRepository.existsById(id);
    }

    @Override
    public List<VitalSign> findAllVitalSign() {
        return vitalSignRepository.findAll();
    }

    @Override
    public VitalSign saveVitalSign(VitalSign vitalSign) {
        return vitalSignRepository.save(vitalSign);
    }

    @Override
    public Optional<VitalSign> getVitalSignById(Long idVitalSign) {
        return Optional.of(vitalSignRepository.getById(idVitalSign));
    }

    @Override
    public void deleteVitalSignById(Long idVitalSign) {
        vitalSignRepository.deleteById(idVitalSign);
    }

    @Override
    public void deleteAllVitalSign() {
        vitalSignRepository.deleteAll();
    }

    @Override
    public List<VitalSign> saveAllVitalSign(List<VitalSign> vitalSigns) {
        return vitalSignRepository.saveAll(vitalSigns);
    }
}
