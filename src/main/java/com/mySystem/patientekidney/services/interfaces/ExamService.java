package com.mySystem.patientekidney.services.interfaces;


import com.mySystem.patientekidney.models.entities.Exam;
import com.mySystem.patientekidney.models.entities.Patient;

import java.util.List;
import java.util.Optional;

public interface ExamService {
    List<Exam> findAllExam();
    Exam saveExam (Exam patient);
    Optional<Exam> getExamById(Long idExam);
    void deleteExamById(Long idExam);
    void deleteAllExam();
    List<Exam> saveAllExam(List<Exam> patients);

}
