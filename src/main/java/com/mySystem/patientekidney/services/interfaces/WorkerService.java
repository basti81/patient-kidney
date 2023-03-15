package com.mySystem.patientekidney.services.interfaces;

import com.mySystem.patientekidney.models.entities.Worker;

import java.util.List;
import java.util.Optional;

public interface WorkerService {
    Boolean existsById(Long id);
    Boolean existsByRut(String rut);
    List<Worker> findAllWorker();
    Worker saveWorker(Worker worker);
    Optional<Worker> getWorkerById(Long idWorker);
    void deleteWorkerById(Long idWorker);
    List<Worker> saveAllWorker(List<Worker> workers);
    void deleteAllWorker();
}
