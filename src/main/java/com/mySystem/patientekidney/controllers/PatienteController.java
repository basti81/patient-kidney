package com.mySystem.patientekidney.controllers;
import com.mySystem.patientekidney.models.entities.Patiente;
import com.mySystem.patientekidney.services.interfaces.PatienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/patiente")
public class PatienteController {
    @Autowired
    private PatienteService patienteService;

    public PatienteController(PatienteService patienteService){
        this.patienteService = patienteService;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Patiente createPatiente(@RequestBody Patiente patiente){
        return patienteService.savePatiente(patiente);
    }

    @GetMapping("/listOfPatiente")
    @ResponseStatus(HttpStatus.OK)
    public List<Patiente> getAllPatiente(){
        return patienteService.findAllPatiente();
    }

    @GetMapping("{id}")
    public ResponseEntity<Patiente> getPatienteById(@PathVariable("id") Long id){
        return patienteService.getPatienteById(id).map(ResponseEntity :: ok)
                .orElseGet(()-> ResponseEntity.notFound().build());
    }

    @PutMapping("{id}")
    public ResponseEntity<Patiente> updatePatiente(@PathVariable("id") Long id,
                                                   @RequestBody Patiente patiente){
        return patienteService.getPatienteById(id)
                .map(savePatiente -> {
                    //savePatiente.setName(patiente.getName());
                    //savePatiente.setEmail(patiente.getEmail());
                    //savePatiente.setPassword(patiente.getPassword());
                    //Patiente updatedPatiente = patienteService.updatePatiente(savePatiente);
                    Patiente updatedPatiente = null;
                    return new ResponseEntity<Patiente>(updatedPatiente,HttpStatus.OK);
                }).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @DeleteMapping("{id}")
    public ResponseEntity<String> deletePatienteById(@PathVariable("id") Long id){
        patienteService.deletePatienteById(id);
        return new ResponseEntity<>("Patiente deleted with successfully!",HttpStatus.OK);
    }

}
