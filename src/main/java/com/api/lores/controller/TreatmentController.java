package com.api.lores.controller;

import com.api.lores.entity.SpecialtyModel;
import com.api.lores.entity.TreatmentModel;
import com.api.lores.services.SpecialtyService;
import com.api.lores.services.TreatmentService;
import org.springframework.beans.BeanUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
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
    @ResponseStatus(HttpStatus.CREATED)
    public TreatmentModel saveTreatment(@RequestBody TreatmentModel treatmentRequest) {
        var treatmentModel = new TreatmentModel();
        BeanUtils.copyProperties(treatmentRequest, treatmentModel);
        return treatmentService.save(treatmentModel);
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<TreatmentModel> getAllTreatments() {
        return treatmentService.findAll();
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public TreatmentModel getSingleTreatment(@PathVariable(value = "id") UUID id) {
        return treatmentService.findOrFail(id);
    }

    @DeleteMapping
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<Object> deleteAllTreatments() {
        treatmentService.deleteAll();
        return ResponseEntity.status(HttpStatus.OK).body("All treatments were deleted successfully.");
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void deleteTreatment(@PathVariable UUID id) {
        treatmentService.deleteById(id);
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