package com.mySystem.patientekidney.controllers;

import com.mySystem.patientekidney.models.entities.Exam;
import com.mySystem.patientekidney.models.entities.Patient;
import com.mySystem.patientekidney.models.entities.Record;
import com.mySystem.patientekidney.services.interfaces.ExamService;
import com.mySystem.patientekidney.services.interfaces.PatientService;
import com.mySystem.patientekidney.services.interfaces.RecordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.lang.reflect.Array;
import java.time.Instant;
import java.util.Optional;

@Controller
@RequestMapping("/exam")
public class ExamController {
    @Autowired
    private ExamService examService;

    @Autowired
    private RecordService recordService;

    @Autowired
    private PatientService patientService;
    public ExamController(ExamService examService){
        this.examService = examService;
    }


    /** New */
    @GetMapping("/new")
    public ModelAndView newForm(@RequestParam("id") Long id,Exam exam){
        ModelAndView mv = new ModelAndView();
        mv.setViewName("/exams/new");
        Optional<Patient> patient = patientService.getPatientById(id);
        mv.addObject("patient",patient.get());
        mv.addObject("exam",exam);
        return mv;
    }

    /** List Exam Patient */
    @GetMapping("/")
    public ModelAndView patientExamList(@RequestParam("id") Long id){
        ModelAndView mv = new ModelAndView();
        mv.setViewName("/exams/list");
        Optional<Patient> patient = patientService.getPatientById(id);
        if(patient.isPresent()){
        }
        mv.addObject("patient",patient.get());
        mv.addObject("listExam", examService.findAllExam());
        return mv;
    }

    /** List Exam */
    @GetMapping("/")
    public ModelAndView list(){
        ModelAndView mv = new ModelAndView();
        mv.setViewName("/exams/list");
        mv.addObject("listExam", examService.findAllExam());
        return mv;
    }


    /** Save and Update Exam*/
    @PostMapping("/create")
    public ModelAndView create(Exam exam, BindingResult result, RedirectAttributes attributes) {
        ModelAndView mv = new ModelAndView();
        mv.setViewName("redirect:/exam/new");

        if (result.hasErrors()) {
            System.out.println("There are mistakes");
            attributes.addFlashAttribute("exam", "The exam was not admitted");
            return mv;
        }


//            attributes.addFlashAttribute("msg",
//                    "The exam has been entered successfully!");


        //Save

            if(!examService.existsById(exam.getId())){
                System.out.println("Entre a not existsByid");
                Exam updatedExam = new Exam();
                mv.addObject("exam",updatedExam);
                attributes.addFlashAttribute("msg",
                        "The exam has been successfully modified!");
                return mv;
            }


        //Update
//        if(examService.existsById(exam.getId())){
//            Exam updatedExam = examService.saveExam(exam);
//            mv.addObject("exam",updatedExam);
//            attributes.addFlashAttribute("msg",
//                    "The exam has been successfully modified!");
//            return mv;
//        }

        mv.addObject("exam",null);
        attributes.addFlashAttribute("exam", "The exam was not admitted");
        return mv;
    }

    /** Detail Exam */
    @GetMapping("/detail")
    public ModelAndView detail(@RequestParam("id") Long id, RedirectAttributes attributes) {
        ModelAndView mv = new ModelAndView();
        if(examService.existsById(id)){
            Optional<Exam> exam = examService.getExamById(id);
            mv.setViewName("/exams/detail");
            mv.addObject("exam",exam.get());
            return mv;
        }
        attributes.addFlashAttribute("msg","Exam not found ;(");
        return mv;
    }



    /** Delete by Id Exam */
    //    @PreAuthorize("hasRole('ADMINISTRADOR')")
    @GetMapping("/delete")
    public ModelAndView delete(@RequestParam("id") Long idUser, RedirectAttributes attributes) {
        ModelAndView mv = new ModelAndView();
        mv.setViewName("redirect:/exam/");
        if(examService.existsById(idUser)){
            System.out.println(("Entre a delete exists"));
            examService.deleteExamById(idUser);
            attributes.addFlashAttribute("msg","Exam successfully removed!");
            return mv;
        }
        attributes.addFlashAttribute("msg","Exam not eliminated");
        return mv;
    }

    /** Delete All Exam*/
    @DeleteMapping("/deleteAll")
    public ModelAndView deleteAll(RedirectAttributes attributes) {
        ModelAndView mv = new ModelAndView();
        mv.setViewName("redirect:/exam/");
        examService.deleteAllExam();
        attributes.addFlashAttribute("msg","All exams removed successfully");
        return mv;
    }

    /** Update Exam*/
    @GetMapping("/update")
    public ModelAndView update(@RequestParam("id") Long idUser) {
        ModelAndView mv = new ModelAndView();
        if(examService.existsById(idUser)){
            Optional<Exam> exam = examService.getExamById(idUser);
            mv.setViewName("/exams/new");
            mv.addObject("exam", exam.get());
            return mv;
        }
        mv.setViewName("redirect:/exam/");
        return mv;
    }
}
