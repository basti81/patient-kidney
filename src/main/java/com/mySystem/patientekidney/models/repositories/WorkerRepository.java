package com.mySystem.patientekidney.models.repositories;

import com.mySystem.patientekidney.models.entities.Worker;
import org.springframework.data.jpa.repository.JpaRepository;

public interface WorkerRepository extends JpaRepository<Worker,Long> {
    Boolean existsByRut(String rut);
}
