package com.mySystem.patientekidney.services;

import com.mySystem.patientekidney.models.entities.Exam;
import com.mySystem.patientekidney.models.repositories.ExamRepository;
import com.mySystem.patientekidney.services.interfaces.ExamService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ExamServiceImpl implements ExamService {

    @Autowired
    private ExamRepository examRepository;
    public ExamServiceImpl(ExamRepository examRepository){
        this.examRepository = examRepository;
    }

    @Override
    public List<Exam> findAllExam() {
        return examRepository.findAll();
    }

    @Override
    public Exam saveExam(Exam exam) {
        return examRepository.save(exam);
    }

    @Override
    public Optional<Exam> getExamById(Long idExam) {
        return Optional.of(examRepository.getById(idExam));
    }

    @Override
    public void deleteExamById(Long idExam) {
        examRepository.deleteById(idExam);
    }

    @Override
    public List<Exam> saveAllExam(List<Exam> exams) {
        return examRepository.saveAll(exams);
    }
}
