package com.mySystem.patientekidney.models.repositories;

import com.mySystem.patientekidney.models.entities.Exam;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ExamRepository extends JpaRepository<Exam,Long> {
    @Query(value = "SELECT * FROM exams WHERE record_id = ?1", nativeQuery = true)
    List<Exam> findAllExamByIdRecord(Long id);
}
