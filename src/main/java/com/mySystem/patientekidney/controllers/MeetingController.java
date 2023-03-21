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

import java.util.List;
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
     * New Time Meeting
     */
    @GetMapping("/time/new")
    public ModelAndView newFormTime(@RequestParam(name = "id", required = true) Long idPatient, Meeting meeting) {
        ModelAndView mv = new ModelAndView();
        mv.setViewName("/meetings/timeTest");
        mv.addObject("meeting",meeting);
        Optional<Patient> patient = patientService.getPatientById(idPatient);
        if(patient.isPresent()){
            mv.addObject("listOfDoctor", workerService.getListOfDoctor());
            mv.addObject("patient",patient.get());
            return mv;
        }
        return new ModelAndView().addObject("patient",null);
    }

    /**
     * New Running Meeting
     */
    @GetMapping("/running/new")
    public ModelAndView newFormRunning(@RequestParam("id") Long idMeeting, Meeting meeting) {
        ModelAndView mv = new ModelAndView();
        mv.setViewName("/meetings/running");
        mv.addObject("meeting",meeting);
        return mv;
    }

    /**
     * List Meeting Patient
     */
    @GetMapping("/byPatient")
    public ModelAndView patientMeetingList(@RequestParam("id") Long idPatient) {
        ModelAndView mv = new ModelAndView();
        Optional<Patient> patient = patientService.getPatientById(idPatient);
        if(patient.isPresent()){
            patient.get().setMeetings(
                    meetingService.findAllByIdPatient(idPatient));
            mv.addObject("patient",patient.get());
            mv.setViewName("/patients/listMeeting");
        }
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
            attributes.addFlashAttribute("msgDelete", "The meeting was not admitted");
            return mv;
        }
        meeting.setPatient(patientService.getPatientById(idPatient).get());
        meeting.setWorker(workerService.getWorkerById(idWorker).get());

        /**
         * Save Meeting
         */
        if (meeting.getIdMeeting() == null) {
            Meeting meetingSaved = meetingService.saveMeeting(meeting);
            if (!meetingSaved.equals(null)) {
                mv.setViewName("redirect:/meeting/new?id=" + meetingSaved.getIdMeeting());
                attributes.addFlashAttribute("msgSave",
                        "The Meeting has been entered successfully!");
                mv.addObject("meeting", meetingSaved);
            }
            return mv;
        }

        /**
         * Update Meeting
         */
        if (meeting.getIdMeeting() != null) {
            Meeting meetingUpdate = meetingService.saveMeeting(meeting);
            if (!meetingUpdate.equals(null)) {
                attributes.addFlashAttribute("msgUpdate", "The meeting has been successfully modified!");
                mv.setViewName("redirect:/meeting/new?id=" + meetingUpdate.getIdMeeting());
                mv.addObject("meeting", meetingUpdate);
            }
            return mv;
        }
        return mv;
    }




    /**
     * Update Time Meeting
     */
    @GetMapping("/time/update")
    public ModelAndView timeUpdate(@RequestParam("idMeeting") Long idMeeting,
                               @RequestParam("idRecord") Long idRecord,
                               RedirectAttributes attributes) {
        ModelAndView mv = new ModelAndView();
        mv.setViewName("/meetings/time");
        return mv;
    }

    /**
     * Update Running Meeting
     */
    @GetMapping("/running/update")
    public ModelAndView runningUpdate(@RequestParam("idMeeting") Long idMeeting,
                               @RequestParam("idRecord") Long idRecord,
                               RedirectAttributes attributes) {
        ModelAndView mv = new ModelAndView();
        mv.setViewName("/meeting/running");
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
     * Delete by idMeeting
     */
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
