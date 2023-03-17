package com.mySystem.patientekidney.controllers;

import com.mySystem.patientekidney.models.entities.VitalSign;
import com.mySystem.patientekidney.models.entities.Record;
import com.mySystem.patientekidney.services.interfaces.PatientService;
import com.mySystem.patientekidney.services.interfaces.RecordService;
import com.mySystem.patientekidney.services.interfaces.VitalSignService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.Optional;

@Controller
@RequestMapping("/vitalSign")
public class VitalSignController {

    @Autowired
    private VitalSignService vitalSignService;
    @Autowired
    private PatientService patientService;

    @Autowired
    private RecordService recordService;


    public VitalSignController(VitalSignService vitalSignService,
                               PatientService patientService,
                               RecordService recordService) {
        this.vitalSignService = vitalSignService;
        this.patientService = patientService;
        this.recordService = recordService;
    }


    /**
     * New
     */
    @GetMapping("/new")
    public ModelAndView newForm(@RequestParam("id") Long idRecord, VitalSign vitalSign) {
        ModelAndView mv = new ModelAndView();
        mv.setViewName("/vitalSigns/new");
        Optional<Record> record = recordService.getRecordById(idRecord);
        mv.addObject("patient", record.get().getPatient());
        mv.addObject("vitalSign", vitalSign);
        return mv;
    }

    /**
     * List VitalSign Patient
     */
    @GetMapping("/byRecord")
    public ModelAndView patientVitalSignList(@RequestParam("id") Long idRecord,
                                             RedirectAttributes attributes) {
        ModelAndView mv = new ModelAndView();
        Optional<Record> record = recordService.getRecordById(idRecord);
        if(record.isPresent()){
            record.get().setVitalSigns(vitalSignService.findAllByIdRecord(idRecord));
            mv.addObject("patient",record.get().getPatient());
            mv.setViewName("/patients/listVitalSign");
            return mv;
        }
        attributes.addFlashAttribute("msg","Patient not found");
        mv.addObject("patient", null);
        return mv;
    }


    /**
     * Save and Update VitalSign
     */
    @PostMapping("/create")
    public ModelAndView create(
            @RequestParam("id") Long idRecord,
            VitalSign vitalSign,
            BindingResult result,
            RedirectAttributes attributes) {
        ModelAndView mv = new ModelAndView();

        if (result.hasErrors()) {
            System.out.println("There are mistakes");
            attributes.addFlashAttribute("vitalSign", "The vitalSign was not admitted");
            return mv;
        }
        Optional<Record> record = recordService.getRecordById(idRecord);
        if (!record.isPresent()) {
            mv.addObject("vitalSign", null);
            attributes.addFlashAttribute("msg", "The vitalSign was not admitted");
        }
        mv.setViewName("redirect:/vitalSign/new?id=" + idRecord);
        vitalSign.setRecord(record.get());
        if (vitalSign.getIdVitalSign() == null) {
            System.out.println(vitalSign.toString());
            VitalSign savedVitalSign = vitalSignService.saveVitalSign(vitalSign);
            mv.addObject("vitalSign", savedVitalSign);
            attributes.addFlashAttribute("msgSave",
                    "The vitalSign has been entered successfully!");

            return mv;
        }
        VitalSign updatedVitalSign = vitalSignService.saveVitalSign(vitalSign);
        mv.addObject("vitalSign", updatedVitalSign);
        attributes.addFlashAttribute("msgUpdate",
                "The vitalSign has been successfully modified!");
        return mv;
    }

    /**
     * Detail VitalSign
     */
    @GetMapping("/detail")
    public ModelAndView detail(@RequestParam("id") Long id, RedirectAttributes attributes) {
        ModelAndView mv = new ModelAndView();
        if (vitalSignService.existsById(id)) {
            Optional<VitalSign> vitalSign = vitalSignService.getVitalSignById(id);
            mv.setViewName("/vitalSigns/detail");
            mv.addObject("vitalSign", vitalSign.get());
            return mv;
        }
        attributes.addFlashAttribute("msgWarning", "VitalSign not found ");
        return mv;
    }


    /**
     * Delete by Id VitalSign
     */
    //    @PreAuthorize("hasRole('ADMINISTRADOR')")
    @GetMapping("/delete")
    public ModelAndView delete(@RequestParam("idVitalSign") Long idVitalSign,
                               @RequestParam("idRecord") Long idRecord,
                               RedirectAttributes attributes) {
        ModelAndView mv = new ModelAndView();
        mv.setViewName("redirect:/vitalSign/byRecord?id=" + idRecord);
        if (vitalSignService.existsById(idVitalSign)) {
            vitalSignService.deleteVitalSignById(idVitalSign);
            attributes.addFlashAttribute("msgDelete", "VitalSign successfully removed!");
            return mv;
        }
        attributes.addFlashAttribute("msgWarning", "VitalSign not eliminated");
        return mv;
    }

    /**
     * Delete All VitalSign
     */
    @DeleteMapping("/deleteAll")
    public ModelAndView deleteAll(RedirectAttributes attributes) {
        ModelAndView mv = new ModelAndView();
        mv.setViewName("redirect:/vitalSign/");
        vitalSignService.deleteAllVitalSign();
        attributes.addFlashAttribute("msg", "All vitalSigns removed successfully");
        return mv;
    }

    /**
     * Update VitalSign
     */
    @GetMapping("/update")
    public ModelAndView update(@RequestParam("idVitalSign") Long idVitalSign,
                               @RequestParam("idRecord") Long idRecord,
                               RedirectAttributes attributes) {
        ModelAndView mv = new ModelAndView();
        if (vitalSignService.existsById(idVitalSign)) {
            System.out.println("Entre a update y existe el vitalSign");
            Optional<VitalSign> vitalSign = vitalSignService.getVitalSignById(idVitalSign);
            mv.setViewName("redirect:/vitalSign/new?id=" + idRecord);
            mv.addObject("vitalSign", vitalSign.get());
            attributes.addFlashAttribute("msgWarning", "Modifying the vitalSign ...");
            return mv;
        }
        mv.setViewName("redirect:/vitalSign/");
        return mv;
    }
}
