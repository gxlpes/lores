package com.api.lores.controller;

import com.api.lores.dto.TreatmentDto;
import com.api.lores.entity.SpecialtyModel;
import com.api.lores.entity.TreatmentModel;
import com.api.lores.service.SpecialtyService;
import com.api.lores.service.TreatmentService;
import org.springframework.beans.BeanUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.UUID;

@CrossOrigin
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
    public TreatmentModel saveTreatment(@RequestBody @Valid TreatmentDto treatmentDto) {
        var treatmentModel = new TreatmentModel();
        BeanUtils.copyProperties(treatmentDto, treatmentModel);
        return treatmentService.save(treatmentModel);
    }

    @GetMapping
    public ResponseEntity<Object> getAllTreatments() {
        return treatmentService.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Object> getSingleTreatment(@PathVariable(value = "id") UUID id) {
        return treatmentService.findById(id);
    }

    @DeleteMapping
    public ResponseEntity<Object> deleteAllTreatments() {
        treatmentService.deleteAll();
        return ResponseEntity.status(HttpStatus.OK).body("All treatments were deleted successfully.");
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> deleteTreatment(@PathVariable UUID id) {
        return treatmentService.deleteById(id);
    }

    @PutMapping("/{treatmentId}/specialty/{specialtyId}")
    @ResponseStatus(HttpStatus.CREATED)
    public TreatmentModel assignSpecialtyToTreatment(@PathVariable UUID treatmentId, @PathVariable UUID specialtyId) {
        TreatmentModel treatment = treatmentService.findOrFail(treatmentId);
        SpecialtyModel specialty = specialtyService.findOrFail(specialtyId);
        treatment.assignSpecialty(specialty);
        return treatmentService.save(treatment);
    }
}