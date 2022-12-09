package com.api.lores.controller;

import com.api.lores.dto.DentistDto;
import com.api.lores.dto.TreatmentDto;
import com.api.lores.exception.NotFoundException;
import com.api.lores.service.specialty.SpecialtyService;
import com.api.lores.service.treatment.TreatmentService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.UUID;

@CrossOrigin(
        allowCredentials = "true",
        origins = "http://localhost:3000",
        allowedHeaders = "*")
@RestController
@RequestMapping("/treatments")
public class TreatmentController {

    final TreatmentService treatmentService;
    final SpecialtyService specialtyService;

    public TreatmentController(TreatmentService treatmentService, SpecialtyService specialtyService) {
        this.treatmentService = treatmentService;
        this.specialtyService = specialtyService;
    }

    @PostMapping
    public ResponseEntity<Object> saveTreatment(@RequestBody @Valid TreatmentDto treatmentDto) {
        return treatmentService.save(treatmentDto);
    }

    @GetMapping
    public ResponseEntity<Object> getAllTreatments() throws NotFoundException {
        return treatmentService.findAll();
    }

    @GetMapping("/{id}")
    public TreatmentDto getSingleTreatment(@PathVariable(value = "id") UUID id) throws NotFoundException {
        return treatmentService.findById(id);
    }

    @DeleteMapping
    public ResponseEntity<String> deleteAllTreatments() throws NotFoundException {
        return treatmentService.deleteAll();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> deleteTreatment(@PathVariable UUID id) throws NotFoundException {
        return treatmentService.deleteById(id);
    }

    @PutMapping("/{id}")
    public ResponseEntity<String> updateTreatment(@PathVariable UUID id, @RequestBody @Valid TreatmentDto treatmentDto) throws NotFoundException {
        return treatmentService.update(id, treatmentDto);
    }

    @PutMapping("/{treatmentId}/specialty/{specialtyId}")
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<Object> assignSpecialtyToTreatment(@PathVariable UUID treatmentId, @PathVariable UUID specialtyId) {
        return treatmentService.assignSpecialtyToTreatment(treatmentId, specialtyId);
    }
}