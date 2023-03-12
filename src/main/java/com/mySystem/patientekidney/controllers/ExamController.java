package com.mySystem.patientekidney.controllers;

import com.mySystem.patientekidney.models.entities.Exam;
import com.mySystem.patientekidney.models.entities.Patient;
import com.mySystem.patientekidney.services.ExamServiceImpl;
import com.mySystem.patientekidney.services.interfaces.ExamService;
import com.mySystem.patientekidney.services.interfaces.PatientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/exam")
public class ExamController {

    @Autowired
    private ExamService examService;

    public ExamController(ExamService examService){
        this.examService = examService;
    }
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Exam createExam(@RequestBody Exam exam){
        return examService.saveExam(exam);
    }

    @PostMapping("/saveAll")
    @ResponseStatus(HttpStatus.CREATED)
    public List<Patient> saveAllPatient(@RequestBody  List<Patient> listOfPatient){
        return patientService.saveAllPatient(listOfPatient);
    }

    @GetMapping("/listOfPatient")
    @ResponseStatus(HttpStatus.OK)
    public List<Patient> getAllPatient(){
        return patientService.findAllPatient();
    }

    @GetMapping("{id}")
    public ResponseEntity<Patient> getPatientById(@PathVariable("id") Long id){
        return patientService.getPatientById(id).map(ResponseEntity :: ok)
                .orElseGet(()-> ResponseEntity.notFound().build());
    }

    @PutMapping("{id}")
    public ResponseEntity<Patient> updatePatient(@PathVariable("id") Long id,
                                                 @RequestBody Patient patient){
        return patientService.getPatientById(id)
                .map(savePatient -> {
                    //savePatient.setName(patient.getName());
                    //savePatient.setEmail(patient.getEmail());
                    //savePatient.setPassword(patient.getPassword());
                    //Patient updatedPatient = patientService.updatePatient(savePatient);
                    Patient updatedPatient = null;
                    return new ResponseEntity<Patient>(updatedPatient,HttpStatus.OK);
                }).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @DeleteMapping("{id}")
    public ResponseEntity<String> deletePatientById(@PathVariable("id") Long id){
        patientService.deletePatientById(id);
        return new ResponseEntity<>("Patient deleted with successfully!",HttpStatus.OK);
    }



}
