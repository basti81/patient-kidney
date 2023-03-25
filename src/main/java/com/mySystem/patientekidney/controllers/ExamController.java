package com.mySystem.patientekidney.controllers;

import com.mySystem.patientekidney.librery.ToolsDiagnosis;
import com.mySystem.patientekidney.models.entities.*;
import com.mySystem.patientekidney.models.entities.Record;
import com.mySystem.patientekidney.services.interfaces.DiagnosisService;
import com.mySystem.patientekidney.services.interfaces.ExamService;
import com.mySystem.patientekidney.services.interfaces.PatientService;
import com.mySystem.patientekidney.services.interfaces.RecordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.Optional;

@Controller
@RequestMapping("/exam")
public class ExamController {
    @Autowired
    private ExamService examService;
    @Autowired
    private PatientService patientService;
    @Autowired
    private RecordService recordService;
    @Autowired
    private DiagnosisService diagnosisService;

    private ToolsDiagnosis tools = new ToolsDiagnosis();
    public ExamController(ExamService examService, PatientService patientService, RecordService recordService, DiagnosisService diagnosisService) {
        this.examService = examService;
        this.patientService = patientService;
        this.recordService = recordService;
        this.diagnosisService = diagnosisService;
    }


    /**
     * New
     */
    @GetMapping("/new")
    public ModelAndView newForm(@RequestParam("id") Long idRecord, Exam exam) {
        ModelAndView mv = new ModelAndView();
        mv.setViewName("/exams/new");
        Optional<Record> record = recordService.getRecordById(idRecord);
        mv.addObject("patient", record.get().getPatient());
        mv.addObject("exam", exam);
        return mv;
    }

    /**
     * List Exam Patient
     */
    @GetMapping("/byRecord")
    public ModelAndView patientExamList(@RequestParam("id") Long idRecord) {
        ModelAndView mv = new ModelAndView();
        Optional<Record> record = recordService.getRecordById(idRecord);
        if (record.isPresent()) {
            record.get().setExams(examService.findAllByIdRecord(idRecord));
            mv.addObject("patient", record.get().getPatient());
            mv.setViewName("/patients/listExam");
            return mv;
        }
        mv.addObject("patient", null);
        return mv;
    }


    /**
     * Save and Update Exam
     */
    @PostMapping("/create")
    public ModelAndView create(
            @RequestParam("id") Long idRecord,
            Exam exam,
            BindingResult result,
            RedirectAttributes attributes) {
        ModelAndView mv = new ModelAndView();

        if (result.hasErrors()) {
            System.out.println("There are mistakes");
            attributes.addFlashAttribute("exam", "The exam was not admitted");
            return mv;
        }
        Optional<Record> record = recordService.getRecordById(idRecord);
        exam.setRecord(record.get());

        /**
         * Save Exam
         */
        if (exam.getIdExam() == null) {
            System.out.println("entre a save exam");
            mv.setViewName("/exams/new");
            exam.setDiagnosis(tools.createDiagnosis(
                    record.get().getPatient(),
                    exam,
                    new Anthropometry(1.0,1.0))
            );
            Exam savedExam = examService.saveExam(exam);
            mv.addObject("patient",record.get().getPatient());
            mv.addObject("exam", savedExam);
            mv.addObject("msgSave","The exam has been entered successfully!");
            return mv;
        }

        /**
         * Update Exam
         */
        if (exam.getIdExam() != null && exam.getDiagnosis() != null) {
            mv.setViewName("/exams/new");
            exam.setDiagnosis(tools.createDiagnosis(record.get().getPatient(),exam,new Anthropometry(0.0,0.0)));
            Exam updatedExam = examService.saveExam(exam);
            mv.addObject("patient",record.get().getPatient());
            mv.addObject("exam", updatedExam);
            mv.addObject("msgUpdate", "The exam has been entered successfully!");
            return mv;
        }
        mv.setViewName("redirect:/exam/new?id="+idRecord);
        attributes.addFlashAttribute("msgWarning", "The exam has problems!");
        return mv;
    }

    /**
     * Detail Exam
     */
    @GetMapping("/detail")
    public ModelAndView detail(@RequestParam("idExam") Long idExam,
                               @RequestParam("idRecord") Long idRecord,
                               RedirectAttributes attributes) {
        ModelAndView mv = new ModelAndView();
        Optional<Record> record = recordService.getRecordById(idRecord);
        if (examService.existsById(idExam) && record.isPresent()) {
            Optional<Exam> exam = examService.getExamById(idExam);
            mv.setViewName("/exams/detail");
            mv.addObject("patient",record.get().getPatient());
            mv.addObject("exam", exam.get());
            return mv;
        }
        mv.setViewName("redirect:/patient/");
        attributes.addFlashAttribute("msgWarning", "Exam not found ");
        return mv;
    }


    /**
     * Delete by Id Exam
     */
    //    @PreAuthorize("hasRole('ADMINISTRADOR')")
    @GetMapping("/delete")
    public ModelAndView delete(@RequestParam("idExam") Long idExam,
                               @RequestParam("idRecord") Long idRecord,
                               RedirectAttributes attributes) {
        ModelAndView mv = new ModelAndView();
        mv.setViewName("redirect:/exam/byRecord?id=" + idRecord);
        if (examService.existsById(idExam)) {
            examService.deleteExamById(idExam);
            attributes.addFlashAttribute("msgDelete", "Exam successfully removed!");
            return mv;
        }
        attributes.addFlashAttribute("msgWarning", "Exam not eliminated");
        return mv;
    }

    /**
     * Delete All Exam
     */
    @DeleteMapping("/deleteAll")
    public ModelAndView deleteAll(RedirectAttributes attributes) {
        ModelAndView mv = new ModelAndView();
        mv.setViewName("redirect:/exam/");
        examService.deleteAllExam();
        attributes.addFlashAttribute("msg", "All exams removed successfully");
        return mv;
    }

    /**
     * Update Exam
     */
    @GetMapping("/update")
    public ModelAndView update(@RequestParam("idExam") Long idExam,
                               @RequestParam("idRecord") Long idRecord,
                               RedirectAttributes attributes) {
        ModelAndView mv = new ModelAndView();
        if (examService.existsById(idExam)) {
            Optional<Exam> exam = examService.getExamById(idExam);
            mv.setViewName("redirect:/exam/new?id=" + idRecord);
            mv.addObject("exam", exam.get());
            attributes.addFlashAttribute("msgWarning", "Modifying the exam ...");
            return mv;
        }
        mv.setViewName("redirect:/exam/");
        return mv;
    }
}
