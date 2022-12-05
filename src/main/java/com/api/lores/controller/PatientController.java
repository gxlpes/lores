package com.api.lores.controller;

import com.api.lores.dto.PatientDto;
import com.api.lores.exception.NotFoundException;
import com.api.lores.service.patient.PatientService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.UUID;

@CrossOrigin(
        allowCredentials = "true",
        origins = "http://localhost:3000",
        allowedHeaders = "*")
@RestController
@RequestMapping("/patients")
public class PatientController {
    final PatientService patientService;

    public PatientController(PatientService patientService) {
        this.patientService = patientService;
    }

    @PostMapping
    @PreAuthorize("hasAnyRole('ROLE_USER', 'ROLE_ADMIN')")
    public ResponseEntity<Object> savePatient(@RequestBody @Valid PatientDto patientDto) {
        return patientService.save(patientDto);
    }

    @GetMapping
    @PreAuthorize("hasAnyRole('ROLE_USER', 'ROLE_ADMIN')")
    public ResponseEntity<Object> getAllPatients() throws NotFoundException {
        return patientService.findAll();
    }

    @GetMapping("/{id}")
    public PatientDto getSinglePatient(@PathVariable UUID id) throws NotFoundException {
        return patientService.findById(id);
    }

    @DeleteMapping
    public ResponseEntity<String> deleteAllPatients() throws NotFoundException {
        return patientService.deleteAll();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> deleteAllPatients(@PathVariable UUID id) throws NotFoundException {
        return patientService.deleteById(id);
    }

    @PutMapping("/{id}")
    public ResponseEntity<String> updatePatient(@PathVariable UUID id, @RequestBody @Valid PatientDto patientDto) throws NotFoundException {
        return patientService.update(id, patientDto);
    }
}