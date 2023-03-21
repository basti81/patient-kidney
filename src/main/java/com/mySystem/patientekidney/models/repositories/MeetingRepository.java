package com.mySystem.patientekidney.models.repositories;

import com.mySystem.patientekidney.models.entities.Exam;
import com.mySystem.patientekidney.models.entities.Meeting;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MeetingRepository extends JpaRepository<Meeting,Long> {
    @Query(value = "SELECT * FROM meeting WHERE patient_id = ?1", nativeQuery = true)
    List<Meeting> findAllByIdPatient(Long id);

    @Query(value = "SELECT * FROM meeting WHERE worker_id = ?1", nativeQuery = true)
    List<Meeting> findAllByIdWorker(Long id);
}
