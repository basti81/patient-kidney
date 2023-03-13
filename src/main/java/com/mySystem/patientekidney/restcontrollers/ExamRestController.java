package com.mySystem.patientekidney.restcontrollers;

import com.mySystem.patientekidney.models.entities.Exam;
import com.mySystem.patientekidney.services.interfaces.ExamService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/exam")
public class ExamRestController {

    @Autowired
    private ExamService examService;

    public ExamRestController(ExamService examService) {
        this.examService = examService;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Exam createExam(@RequestBody Exam exam) {
        return examService.saveExam(exam);
    }

    @PostMapping("/saveAll")
    @ResponseStatus(HttpStatus.CREATED)
    public List<Exam> saveAllExam(@RequestBody List<Exam> listOfExam) {
        return examService.saveAllExam(listOfExam);
    }

    @GetMapping("/listOfExam")
    @ResponseStatus(HttpStatus.OK)
    public List<Exam> getAllExam() {
        return examService.findAllExam();
    }

    @GetMapping("{id}")
    public ResponseEntity<Exam> getExamById(@PathVariable("id") Long id) {
        return examService.getExamById(id).map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PutMapping("{id}")
    public ResponseEntity<Exam> updateExam(@PathVariable("id") Long id,
                                           @RequestBody Exam exam) {
        return examService.getExamById(id)
                .map(saveExam -> {
                    //saveExam.setName(patient.getName());
                    //saveExam.setEmail(patient.getEmail());
                    //saveExam.setPassword(patient.getPassword());
                    //Exam updatedExam = examService.updateExam(saveExam);
                    Exam updatedExam = null;
                    return new ResponseEntity<Exam>(updatedExam, HttpStatus.OK);
                }).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @DeleteMapping("{id}")
    public ResponseEntity<String> deleteExamById(@PathVariable("id") Long id) {
        examService.deleteExamById(id);
        return new ResponseEntity<>("Exam deleted with successfully!", HttpStatus.OK);
    }


}
