package com.mySystem.patientekidney.services;

import com.mySystem.patientekidney.models.entities.Anthropometry;
import com.mySystem.patientekidney.models.repositories.AnthropometryRepository;
import com.mySystem.patientekidney.services.interfaces.AnthropometryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AnthropometryServiceImpl implements AnthropometryService {

    @Autowired
    private AnthropometryRepository anthropometryRepository;
    public AnthropometryServiceImpl(AnthropometryRepository anthropometryRepository){
        this.anthropometryRepository = anthropometryRepository;
    }
    @Override
    public List<Anthropometry> findAllByIdRecord(Long id) {
        return null;
    }

    @Override
    public Boolean existsById(Long id) {
        return anthropometryRepository.existsById(id);
    }

    @Override
    public List<Anthropometry> findAllAnthropometry() {
        return null;
    }

    @Override
    public Anthropometry saveAnthropometry(Anthropometry anthropometry) {
        return anthropometryRepository.save(anthropometry);
    }

    @Override
    public Optional<Anthropometry> getAnthropometryById(Long idAnthropometry) {
        return Optional.of(anthropometryRepository.getById(idAnthropometry));
    }

    @Override
    public void deleteAnthropometryById(Long idAnthropometry) {
        anthropometryRepository.deleteById(idAnthropometry);
    }

    @Override
    public void deleteAllAnthropometry() {

    }

    @Override
    public List<Anthropometry> saveAllAnthropometry(List<Anthropometry> anthropometries) {
        return anthropometryRepository.saveAll(anthropometries);
    }
}
