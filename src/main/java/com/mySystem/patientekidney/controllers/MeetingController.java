package com.mySystem.patientekidney.controllers;

import com.mySystem.patientekidney.models.entities.Meeting;
import com.mySystem.patientekidney.models.entities.Patient;
import com.mySystem.patientekidney.models.entities.Worker;
import com.mySystem.patientekidney.services.interfaces.MeetingService;
import com.mySystem.patientekidney.services.interfaces.PatientService;
import com.mySystem.patientekidney.services.interfaces.WorkerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.Optional;


@Controller
@RequestMapping("/meeting")
public class MeetingController {

    @Autowired
    private MeetingService meetingService;
    @Autowired
    private PatientService patientService;
    @Autowired
    private WorkerService workerService;

    public MeetingController(MeetingService meetingService, PatientService patientService, WorkerService workerService) {
        this.meetingService = meetingService;
        this.patientService = patientService;
        this.workerService = workerService;
    }


    /**
     * New
     */
    @GetMapping("/new")
    public ModelAndView newForm(@RequestParam("id") Long idMeeting, Meeting meeting) {
        ModelAndView mv = new ModelAndView();

        return mv;
    }

    /**
     * List Meeting Patient
     */
    @GetMapping("/byPatient")
    public ModelAndView patientMeetingList(@RequestParam("id") Long idPatient) {
        ModelAndView mv = new ModelAndView();
        return mv;
    }

    /**
     * List Meeting Worker
     */
    @GetMapping("/byWorker")
    public ModelAndView workerMeetingList(@RequestParam("id") Long idWorker) {
        ModelAndView mv = new ModelAndView();
        return mv;
    }


    /**
     * Save and Update Meeting
     */
    @PostMapping("/create")
    public ModelAndView create(
            @RequestParam("idPatient") Long idPatient,
            @RequestParam("idWorker") Long idWorker,
            Meeting meeting,
            BindingResult result,
            RedirectAttributes attributes) {
        ModelAndView mv = new ModelAndView();
        if (result.hasErrors()) {
            System.out.println("There are mistakes");
            attributes.addFlashAttribute("meeting", "The meeting was not admitted");
            return mv;
        }
        Optional<Patient> patient = patientService.getPatientById(idPatient);
        Optional<Worker> worker = workerService.getWorkerById(idWorker);
        if(patient.isPresent() && worker.isPresent()){
            meeting.setPatient(patient.get());
            meeting.setWorker(worker.get());
            meetingService.saveMeeting(meeting);
        }
        return mv;
    }


    /**
     * Update Meeting
     */
    @GetMapping("/update")
    public ModelAndView update(@RequestParam("idMeeting") Long idMeeting,
                               @RequestParam("idRecord") Long idRecord,
                               RedirectAttributes attributes) {
        ModelAndView mv = new ModelAndView();

        return mv;
    }

    /**
     * Detail Meeting
     */
    @GetMapping("/detail")
    public ModelAndView detail(@RequestParam("id") Long id, RedirectAttributes attributes) {
        ModelAndView mv = new ModelAndView();
        return mv;
    }


    /**
     * In - Meeting
     */
    @GetMapping("/inMeeting")
    public ModelAndView inMeeting(@RequestParam("id") Long idMeeting, RedirectAttributes attributes) {
        ModelAndView mv = new ModelAndView();
        mv.setViewName("");
        return mv;
    }


    /**
     * Delete by Id Meeting
     */
    //    @PreAuthorize("hasRole('ADMINISTRADOR')")
    @GetMapping("/delete")
    public ModelAndView delete(@RequestParam("idMeeting") Long idMeeting,
                               @RequestParam("idRecord") Long idRecord,
                               RedirectAttributes attributes) {
        ModelAndView mv = new ModelAndView();

        return mv;
    }

    /**
     * Delete All Meeting
     */
    @DeleteMapping("/deleteAll")
    public ModelAndView deleteAll(RedirectAttributes attributes) {
        ModelAndView mv = new ModelAndView();

        return mv;
    }

}
