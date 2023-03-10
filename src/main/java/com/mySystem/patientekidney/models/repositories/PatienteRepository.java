package com.mySystem.patientekidney.models.repositories;


import com.mySystem.patientekidney.models.entities.Patiente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PatienteRepository extends JpaRepository<Patiente,Long> {
}
