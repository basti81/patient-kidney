package com.mySystem.patientekidney.models.repositories;

import com.mySystem.patientekidney.models.entities.VitalSign;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface VitalSignRepository extends JpaRepository<VitalSign, Long> {
    List<VitalSign> findAllByIdRecord(Long id);
}
