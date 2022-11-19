package com.api.lores.entities.treatments;

import com.api.lores.entities.specialty.SpecialtyService;
import com.api.lores.entities.specialty.SpecialtyModel;
import org.springframework.beans.BeanUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
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
    public ResponseEntity<Object> saveTreatment(@RequestBody TreatmentModel treatmentRequest) {
        var treatmentModel = new TreatmentModel();
        BeanUtils.copyProperties(treatmentRequest, treatmentModel);
        return ResponseEntity.status(HttpStatus.CREATED).body(treatmentService.save(treatmentModel));
    }

    @GetMapping
    public ResponseEntity<List<TreatmentModel>> getAllTreatments() {
        return ResponseEntity.status(HttpStatus.OK).body(treatmentService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Object> getSingleTreatment(@PathVariable(value = "id") UUID id) {
        Optional<TreatmentModel> treatmentModelOptional = treatmentService.findById(id);
        if (treatmentModelOptional.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Treatment not found.");
        } else {
            return ResponseEntity.status(HttpStatus.OK).body(treatmentModelOptional.get());
        }
    }

    @DeleteMapping
    public ResponseEntity<Object> deleteAllTreatments() {
        treatmentService.deleteAll();
        return ResponseEntity.status(HttpStatus.OK).body("All treatments were deleted successfully.");
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> deleteTreatment(@PathVariable(value = "id") UUID id) {
        Optional<TreatmentModel> treatmentModelOptional = treatmentService.findById(id);
        if (treatmentModelOptional.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Treatment not found.");
        } else {
            treatmentService.delete(treatmentModelOptional.get());
            return ResponseEntity.status(HttpStatus.OK).body("Treatment deleted successfully.");
        }
    }

    @PutMapping("/{treatmentId}/specialty/{specialtyId}")
    public ResponseEntity<Object> assignSpecialtyToTreatment(@PathVariable UUID treatmentId, @PathVariable UUID specialtyId) {
        TreatmentModel treatment = treatmentService.findById(treatmentId).get();
        SpecialtyModel specialty = specialtyService.findById(specialtyId).get();
        treatment.assignSpecialty(specialty);
        return ResponseEntity.status(HttpStatus.CREATED).body(treatmentService.save(treatment));
    }
}
