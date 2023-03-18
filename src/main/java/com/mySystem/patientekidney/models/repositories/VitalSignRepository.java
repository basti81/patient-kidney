package com.mySystem.patientekidney.models.repositories;

import com.mySystem.patientekidney.models.entities.Exam;
import com.mySystem.patientekidney.models.entities.VitalSign;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface VitalSignRepository extends JpaRepository<VitalSign, Long> {
    @Query(value = "SELECT * FROM vital_signs WHERE record_id = ?1", nativeQuery = true)
    List<VitalSign> findAllByIdRecord(Long id);
}
