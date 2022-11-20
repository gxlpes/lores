package com.api.lores.controller;

import com.api.lores.dto.PatientDto;
import com.api.lores.service.PatientService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.UUID;

@RestController
@CrossOrigin
@RequestMapping("/patients")
public class PatientController {
    final PatientService patientService;

    public PatientController(PatientService patientService) {
        this.patientService = patientService;
    }

    @PostMapping
    public ResponseEntity<Object> savePatient(@RequestBody @Valid PatientDto patientDto) {
        return patientService.save(patientDto);
    }

    @GetMapping
    public ResponseEntity<Object> getAllPatients() {
        return patientService.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Object> getSinglePatient(@PathVariable UUID id) {
        return patientService.findById(id);
    }

    @DeleteMapping
    public ResponseEntity<String> deleteAllPatients() {
        return patientService.deleteAll();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> deleteAllPatients(@PathVariable UUID id) {
       return patientService.deleteById(id);
    }

    @PutMapping("/{id}")
    public ResponseEntity<String> updatePatient(@PathVariable UUID id, @RequestBody @Valid PatientDto patientDto) {
        return patientService.updatePatient(id, patientDto);
    }
}