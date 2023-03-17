package com.mySystem.patientekidney.controllers;


import com.mySystem.patientekidney.models.entities.Patient;
import com.mySystem.patientekidney.services.interfaces.PatientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.time.Instant;
import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/patient")
public class PatientController {

    @Autowired
    private PatientService patientService;

    public PatientController(PatientService patientService){
        this.patientService = patientService;
    }


   /** New */
   @GetMapping("/new")
   public ModelAndView newForm(Patient patient){
       ModelAndView mv = new ModelAndView();
       mv.setViewName("/patients/new");
       mv.addObject("patient",patient);
       return mv;
   }

    /** Save and Update Patient*/
    @PostMapping("/create")
    public ModelAndView create(Patient patient, BindingResult result, RedirectAttributes attributes,
                               @RequestParam("fileProfile") MultipartFile multiPart) {
        ModelAndView mv = new ModelAndView();
        mv.setViewName("redirect:/patient/new");

        if (result.hasErrors()) {
            System.out.println("There are mistakes");
            attributes.addFlashAttribute("patient", "The patient was not admitted");
            return mv;
        }

        /*if (!multiPart.isEmpty()) {
            String ruta = "C:/Users/basti/Documents/Trabajo/appWebSirere/sistema_registro_renal/src/main/resources/static/img/users/";
            String nombreImagen = Utility.saveFile(multiPart, ruta);
            if (nombreImagen != null){ // La imagen si se subio
                // Procesamos la variable nombreImagen
                usuario.setImg(nombreImagen);
            }
        }*/

        if(!patientService.existsByRut(patient.getRut())){
            patient.setStartDate(Instant.now());
            Patient savedPatient = patientService.savePatient(patient);
            mv.addObject("patient",savedPatient);
            attributes.addFlashAttribute("msgSave",
                    "The patient "+patient.getName()+" has been entered successfully!");
            return mv;
        }

        if(patientService.existsById(patient.getId())){
            Patient updatedPatient = patientService.savePatient(patient);
            mv.addObject("patient",updatedPatient);
            attributes.addFlashAttribute("msgUpdate",
                    "The patient "+patient.getName()+" has been successfully modified!");
            return mv;
        }
        mv.addObject("patient",null);
        attributes.addFlashAttribute("msgDelete", "The patient was not admitted");
        return mv;
    }

    /** Detail Patient */
    @GetMapping("/detail")
    public ModelAndView detail(@RequestParam("id") Long id, RedirectAttributes attributes) {
        ModelAndView mv = new ModelAndView();
        if(patientService.existsById(id)){
            Optional<Patient> patient = patientService.getPatientById(id);
            mv.setViewName("/patients/detail");
            mv.addObject("patient",patient.get());
            return mv;
        }
        attributes.addFlashAttribute("msg","Patient not found ;(");
        return mv;
    }

    /** List Patient */
    @GetMapping("/")
    public ModelAndView list(){
        ModelAndView mv = new ModelAndView();
        mv.setViewName("/patients/list");
        mv.addObject("listPatient", patientService.findAllPatient());
        return mv;
    }

    /** Delete by Id Patient */
    //    @PreAuthorize("hasRole('ADMINISTRADOR')")
    @GetMapping("/delete")
    public ModelAndView delete(@RequestParam("id") Long id, RedirectAttributes attributes) {
        ModelAndView mv = new ModelAndView();
        mv.setViewName("redirect:/patient/");
        if(patientService.existsById(id)){
            patientService.deletePatientById(id);
            attributes.addFlashAttribute("msgDelete","Patient successfully removed!");
            return mv;
        }
        attributes.addFlashAttribute("msgWarning","Patient not eliminated");
        return mv;
    }

    /** Delete All Patient*/
    @DeleteMapping("/deleteAll")
    public ModelAndView deleteAll(RedirectAttributes attributes) {
        ModelAndView mv = new ModelAndView();
        mv.setViewName("redirect:/patient/");
        patientService.deleteAllPatient();
        attributes.addFlashAttribute("msg","All patients removed successfully");
        return mv;
    }

    /** Update Patient*/
    @GetMapping("/update")
    public ModelAndView update(@RequestParam("id") Long id, RedirectAttributes attributes) {
        ModelAndView mv = new ModelAndView();
        if(patientService.existsById(id)){
            Optional<Patient> patient = patientService.getPatientById(id);
            mv.setViewName("redirect:/patient/new");
            mv.addObject("patient", patient.get());
            attributes.addFlashAttribute("msgWarning", "Modifying the Patient ...");
            return mv;
        }
        mv.setViewName("redirect:/patient/");
        return mv;
    }

}
