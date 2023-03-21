package com.mySystem.patientekidney.controllers;

import com.mySystem.patientekidney.models.entities.Patient;
import com.mySystem.patientekidney.models.entities.Worker;
import com.mySystem.patientekidney.services.interfaces.PatientService;
import com.mySystem.patientekidney.services.interfaces.WorkerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.Optional;

@Controller
@RequestMapping("/worker")
public class WorkerController {

    @Autowired
    private WorkerService workerService;

    public WorkerController(WorkerService workerService){
        this.workerService = workerService;
    }


    /** New */
    @GetMapping("/new")
    public ModelAndView newForm(Worker worker){
        ModelAndView mv = new ModelAndView();
        mv.setViewName("/workers/new");
        mv.addObject("worker",worker);
        return mv;
    }

    /** Save and Update Worker*/
    @PostMapping("/create")
    public ModelAndView create(Worker worker, BindingResult result, RedirectAttributes attributes ) {
        ModelAndView mv = new ModelAndView();
        mv.setViewName("redirect:/worker/new");

        if (result.hasErrors()) {
            System.out.println("There are mistakes");
            attributes.addFlashAttribute("worker", "The worker was not admitted");
            return mv;
        }

        

        if(!workerService.existsByRut(worker.getRut())){
            Worker savedWorker = workerService.saveWorker(worker);
            mv.addObject("worker",savedWorker);
            attributes.addFlashAttribute("msg",
                    "The worker "+worker.getForename()+" has been entered successfully!");
            return mv;
        }

        if(workerService.existsById(worker.getId())){
            Worker updatedWorker = workerService.saveWorker(worker);
            mv.addObject("worker",updatedWorker);
            attributes.addFlashAttribute("msg",
                    "The worker "+worker.getForename()+" has been successfully modified!");
            return mv;
        }
        mv.addObject("worker",null);
        attributes.addFlashAttribute("worker", "The worker was not admitted");
        return mv;
    }

    /** Detail Worker */
    @GetMapping("/detail")
    public ModelAndView detail(@RequestParam("id") Long id, RedirectAttributes attributes) {
        ModelAndView mv = new ModelAndView();
        if(workerService.existsById(id)){
            Optional<Worker> worker = workerService.getWorkerById(id);
            mv.setViewName("/workers/detail");
            mv.addObject("worker",worker.get());
            return mv;
        }
        attributes.addFlashAttribute("msg","Worker not found ;(");
        return mv;
    }

    /** List Worker */
    @GetMapping("/")
    public ModelAndView list(){
        ModelAndView mv = new ModelAndView();
        mv.setViewName("/workers/list");
        mv.addObject("listWorker", workerService.findAllWorker());
        return mv;
    }

    /** Delete by Id Worker */
    //    @PreAuthorize("hasRole('ADMINISTRADOR')")
    @GetMapping("/delete")
    public ModelAndView delete(@RequestParam("id") Long idUser, RedirectAttributes attributes) {
        ModelAndView mv = new ModelAndView();
        mv.setViewName("redirect:/worker/");
        if(workerService.existsById(idUser)){
            System.out.println(("Entre a delete exists"));
            workerService.deleteWorkerById(idUser);
            attributes.addFlashAttribute("msg","Worker successfully removed!");
            return mv;
        }
        attributes.addFlashAttribute("msg","Worker not eliminated");
        return mv;
    }

    /** Delete All Worker*/
    @DeleteMapping("/deleteAll")
    public ModelAndView deleteAll(RedirectAttributes attributes) {
        ModelAndView mv = new ModelAndView();
        mv.setViewName("redirect:/worker/");
        workerService.deleteAllWorker();
        attributes.addFlashAttribute("msg","All workers removed successfully");
        return mv;
    }

    /** Update Worker*/
    @GetMapping("/update")
    public ModelAndView update(@RequestParam("id") Long idUser) {
        ModelAndView mv = new ModelAndView();
        if(workerService.existsById(idUser)){
            Optional<Worker> worker = workerService.getWorkerById(idUser);
            mv.setViewName("/workers/new");
            mv.addObject("worker", worker.get());
            return mv;
        }
        mv.setViewName("redirect:/worker/");
        return mv;
    }

}
