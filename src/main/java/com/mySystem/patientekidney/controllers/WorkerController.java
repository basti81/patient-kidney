package com.mySystem.patientekidney.controllers;


import com.mySystem.patientekidney.models.entities.Worker;
import com.mySystem.patientekidney.services.interfaces.WorkerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/worker")
public class WorkerController {

    @Autowired
    private WorkerService workerService;

    public WorkerController(WorkerService workerService){
        this.workerService = workerService;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Worker createWorker(@RequestBody Worker patiente){
        return workerService.saveWorker(patiente);
    }

    @GetMapping("/listOfWorker")
    @ResponseStatus(HttpStatus.OK)
    public List<Worker> getAllWorker(){
        return workerService.findAllWorker();
    }

    @GetMapping("{id}")
    public ResponseEntity<Worker> getWorkerById(@PathVariable("id") Long id){
        return workerService.getWorkerById(id).map(ResponseEntity :: ok)
                .orElseGet(()-> ResponseEntity.notFound().build());
    }

    @PutMapping("{id}")
    public ResponseEntity<Worker> updateWorker(@PathVariable("id") Long id,
                                                   @RequestBody Worker patiente){
        return workerService.getWorkerById(id)
                .map(saveWorker -> {
                    //saveWorker.setName(patiente.getName());
                    //saveWorker.setEmail(patiente.getEmail());
                    //saveWorker.setPassword(patiente.getPassword());
                    //Worker updatedWorker = workerService.updateWorker(saveWorker);
                    Worker updatedWorker = null;
                    return new ResponseEntity<Worker>(updatedWorker,HttpStatus.OK);
                }).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @DeleteMapping("{id}")
    public ResponseEntity<String> deleteWorkerById(@PathVariable("id") Long id){
        workerService.deleteWorkerById(id);
        return new ResponseEntity<>("Worker deleted with successfully!",HttpStatus.OK);
    }


}
