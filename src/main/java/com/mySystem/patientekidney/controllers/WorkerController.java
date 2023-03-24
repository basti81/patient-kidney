package com.mySystem.patientekidney.controllers;

import com.mySystem.patientekidney.librery.StateRole;
import com.mySystem.patientekidney.librery.StateSpecialty;
import com.mySystem.patientekidney.models.entities.Patient;
import com.mySystem.patientekidney.models.entities.Worker;
import com.mySystem.patientekidney.services.interfaces.PatientService;
import com.mySystem.patientekidney.services.interfaces.UserService;
import com.mySystem.patientekidney.services.interfaces.WorkerService;
import org.apache.catalina.LifecycleState;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/worker")
public class WorkerController {

    @Autowired
    private WorkerService workerService;

    @Autowired
    private UserService userService;

    public WorkerController(WorkerService workerService, UserService userService) {
        this.workerService = workerService;
        this.userService = userService;
    }

    /**
     * New
     */
    @GetMapping("/new")
    public ModelAndView newForm(Worker worker) {
        ModelAndView mv = new ModelAndView();
        List<StateSpecialty> listStateSpecialty = Arrays.stream(StateSpecialty.values()).toList();
        List<StateRole> listStateRole = Arrays.stream(StateRole.values()).toList();
        mv.setViewName("/workers/new");
        mv.addObject("worker", worker);
        mv.addObject("selectSpecialty", listStateSpecialty);
        mv.addObject("selectRole",listStateRole);
        return mv;
    }

    /**
     * Save and Update Worker
     */
    @PostMapping("/create")
    public ModelAndView create(Worker worker, BindingResult result, RedirectAttributes attributes) {
        ModelAndView mv = new ModelAndView();
        if (result.hasErrors()) {
            System.out.println("There are mistakes");
            attributes.addFlashAttribute("worker", "The worker was not admitted");
            return mv;
        }

        /**
         * Save Worker
         */
        if (worker.getId() == null) {
            mv.setViewName("/workers/new");
            if (userService.existsByRut(worker.getRut())) {
                mv.addObject("msgDelete",
                        "'Rut - " + worker.getRut() + "' is already registered in the system");
                mv.addObject("worker", worker);
                return mv;
            }
            if (userService.existsByMail(worker.getMail())) {
                mv.addObject("msgDelete",
                        "'Mail - " + worker.getMail() + "' is already registered in the system");
                mv.addObject("worker", worker);
                return mv;
            }

            Worker savedWorker = workerService.saveWorker(worker);
            mv.addObject("worker", savedWorker);
            mv.addObject("msgSave",
                    "The worker " + worker.getForename() + " has been entered successfully!");
            return mv;
        }

        /**
         * Update Worker
         */
        if (worker.getId() != null) {
            mv.setViewName("redirect:/worker/new");
            Worker updatedWorker = workerService.saveWorker(worker);
            mv.addObject("worker", updatedWorker);
            attributes.addFlashAttribute("msgUpdate",
                    "The worker " + worker.getForename() + " has been successfully modified!");
            return mv;
        }

        mv.addObject("worker", worker);
        attributes.addFlashAttribute("msgWarning", "The worker was not admitted");
        return mv;
    }

    /**
     * Detail Worker
     */
    @GetMapping("/detail")
    public ModelAndView detail(@RequestParam("id") Long id, RedirectAttributes attributes) {
        ModelAndView mv = new ModelAndView();
        if (workerService.existsById(id)) {
            Optional<Worker> worker = workerService.getWorkerById(id);
            mv.setViewName("/workers/detail");
            mv.addObject("worker", worker.get());
            return mv;
        }
        attributes.addFlashAttribute("msg", "Worker not found ;(");
        return mv;
    }

    /**
     * List Worker
     */
    @GetMapping("/")
    public ModelAndView list() {
        ModelAndView mv = new ModelAndView();
        mv.setViewName("/workers/list");
        mv.addObject("listWorker", workerService.findAllWorker());
        return mv;
    }

    /**
     * Delete by Id Worker
     */
    //    @PreAuthorize("hasRole('ADMINISTRADOR')")
    @GetMapping("/delete")
    public ModelAndView delete(@RequestParam("id") Long idUser, RedirectAttributes attributes) {
        ModelAndView mv = new ModelAndView();
        mv.setViewName("redirect:/worker/");
        if (workerService.existsById(idUser)) {
            workerService.deleteWorkerById(idUser);
            attributes.addFlashAttribute("msgDelete", "Worker successfully removed!");
            return mv;
        }
        attributes.addFlashAttribute("msgWarning", "Worker not eliminated");
        return mv;
    }

    /**
     * Delete All Worker
     */
    @DeleteMapping("/deleteAll")
    public ModelAndView deleteAll(RedirectAttributes attributes) {
        ModelAndView mv = new ModelAndView();
        mv.setViewName("redirect:/worker/");
        workerService.deleteAllWorker();
        attributes.addFlashAttribute("msg", "All workers removed successfully");
        return mv;
    }

    /**
     * Update Worker
     */
    @GetMapping("/update")
    public ModelAndView update(@RequestParam("id") Long id, RedirectAttributes attributes) {
        ModelAndView mv = new ModelAndView();
        if (workerService.existsById(id)) {
            Optional<Worker> worker = workerService.getWorkerById(id);
            mv.setViewName("redirect:/worker/new");
            mv.addObject("worker", worker.get());
            attributes.addFlashAttribute("msgWarning", "Modifying the worker ...");
            return mv;
        }
        mv.setViewName("redirect:/worker/");
        return mv;
    }



}
