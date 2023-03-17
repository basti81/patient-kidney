package com.mySystem.patientekidney.restcontrollers;

import com.mySystem.patientekidney.models.entities.VitalSign;
import com.mySystem.patientekidney.services.interfaces.ExamService;
import com.mySystem.patientekidney.services.interfaces.VitalSignService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@RestController
@RequestMapping("/api/vitalSign")

public class VitalSignRestController {

    @Autowired
    private VitalSignService vitalSignService;

    public VitalSignRestController(VitalSignService vitalSignService) {
        this.vitalSignService = vitalSignService;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public VitalSign createVitalSign(@RequestBody VitalSign vitalSign) {
        return vitalSignService.saveVitalSign(vitalSign);
    }

    @PostMapping("/saveAll")
    @ResponseStatus(HttpStatus.CREATED)
    public List<VitalSign> saveAllVitalSign(@RequestBody List<VitalSign> listOfVitalSign) {
        return vitalSignService.saveAllVitalSign(listOfVitalSign);
    }

    @GetMapping("/listOfVitalSign")
    @ResponseStatus(HttpStatus.OK)
    public List<VitalSign> getAllVitalSign() {
        return vitalSignService.findAllVitalSign();
    }

    @GetMapping("{id}")
    public ResponseEntity<VitalSign> getVitalSignById(@PathVariable("id") Long id) {
        return vitalSignService.getVitalSignById(id).map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PutMapping("{id}")
    public ResponseEntity<VitalSign> updateVitalSign(@PathVariable("id") Long id,
                                           @RequestBody VitalSign vitalSign) {
        return vitalSignService.getVitalSignById(id)
                .map(saveVitalSign -> {
                    //saveVitalSign.setName(patient.getName());
                    //saveVitalSign.setEmail(patient.getEmail());
                    //saveVitalSign.setPassword(patient.getPassword());
                    //VitalSign updatedVitalSign = vitalSignService.updateVitalSign(saveVitalSign);
                    VitalSign updatedVitalSign = null;
                    return new ResponseEntity<VitalSign>(vitalSign, HttpStatus.OK);
                }).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @DeleteMapping("{id}")
    public ResponseEntity<String> deleteVitalSignById(@PathVariable("id") Long id) {
        vitalSignService.deleteVitalSignById(id);
        return new ResponseEntity<>("VitalSign deleted with successfully!", HttpStatus.OK);
    }

    @DeleteMapping("/deleteAll")
    public ResponseEntity<String> deleteAllVitalSign(){
        vitalSignService.deleteAllVitalSign();
        return new ResponseEntity<>("All VitalSigns deleted with successfully!", HttpStatus.OK);
    }

}
