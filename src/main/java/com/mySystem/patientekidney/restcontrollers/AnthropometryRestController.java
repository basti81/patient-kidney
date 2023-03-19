package com.mySystem.patientekidney.restcontrollers;

import com.mySystem.patientekidney.models.entities.Anthropometry;
import com.mySystem.patientekidney.services.interfaces.AnthropometryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/Anthropometry")
public class AnthropometryRestController {
    @Autowired
    private AnthropometryService anthropometryService;

    public AnthropometryRestController(AnthropometryService anthropometryService) {
        this.anthropometryService = anthropometryService;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Anthropometry createAnthropometry(@RequestBody Anthropometry anthropometry) {
        return anthropometryService.saveAnthropometry(anthropometry);
    }

    @PostMapping("/saveAll")
    @ResponseStatus(HttpStatus.CREATED)
    public List<Anthropometry> saveAllAnthropometry(@RequestBody List<Anthropometry> listOfAnthropometry) {
        return anthropometryService.saveAllAnthropometry(listOfAnthropometry);
    }

    @GetMapping("/listOfAnthropometry")
    @ResponseStatus(HttpStatus.OK)
    public List<Anthropometry> getAllAnthropometry() {
        return anthropometryService.findAllAnthropometry();
    }

    @GetMapping("{id}")
    public ResponseEntity<Anthropometry> getAnthropometryById(@PathVariable("id") Long id) {
        return anthropometryService.getAnthropometryById(id).map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PutMapping("{id}")
    public ResponseEntity<Anthropometry> updateAnthropometry(@PathVariable("id") Long id,
                                           @RequestBody Anthropometry anthropometry) {
        return anthropometryService.getAnthropometryById(id)
                .map(saveAnthropometry -> {
                    //saveAnthropometry.setName(patient.getName());
                    //saveAnthropometry.setEmail(patient.getEmail());
                    //saveAnthropometry.setPassword(patient.getPassword());
                    //Anthropometry updatedAnthropometry = anthropometryService.updateAnthropometry(saveAnthropometry);
                    Anthropometry updatedAnthropometry = null;
                    return new ResponseEntity<Anthropometry>(updatedAnthropometry, HttpStatus.OK);
                }).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @DeleteMapping("{id}")
    public ResponseEntity<String> deleteAnthropometryById(@PathVariable("id") Long id) {
        anthropometryService.deleteAnthropometryById(id);
        return new ResponseEntity<>("Anthropometry deleted with successfully!", HttpStatus.OK);
    }

    @DeleteMapping("/deleteAll")
    public ResponseEntity<String> deleteAllAnthropometry(){
        anthropometryService.deleteAllAnthropometry();
        return new ResponseEntity<>("All Anthropometries deleted with successfully!", HttpStatus.OK);
    }
}
