package com.mySystem.patientekidney.services;

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

    public VitalSignServiceImpl(VitalSignRepository vitalSignRepository){
        this.vitalSignRepository = vitalSignRepository;
    }

    @Override
    public List<VitalSign> findAllByIdRecord(Long id) {
        return vitalSignRepository.findAllByIdRecord(id);
    }

    @Override
    public List<VitalSign> findAllByIdVitalSign(Long id) {
        return null;
    }

    @Override
    public Boolean existsById(Long id) {
        return null;
    }

    @Override
    public List<VitalSign> findAllVitalSign() {
        return null;
    }

    @Override
    public VitalSign saveVitalSign(VitalSign vitalSign) {
        return null;
    }

    @Override
    public Optional<VitalSign> getVitalSignById(Long idVitalSign) {
        return Optional.empty();
    }

    @Override
    public void deleteVitalSignById(Long idVitalSign) {

    }

    @Override
    public void deleteAllVitalSign() {

    }

    @Override
    public List<VitalSign> saveAllVitalSign(List<VitalSign> vitalSigns) {
        return null;
    }
}
