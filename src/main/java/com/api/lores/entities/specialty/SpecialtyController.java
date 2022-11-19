package com.api.lores.entities.specialty;

import com.api.lores.entities.dentist.DentistModel;
import com.api.lores.entities.dentist.DentistService;
import org.springframework.beans.BeanUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@CrossOrigin
@RestController
@RequestMapping("/specialties")
public class SpecialtyController {

    final SpecialtyService specialtyService;
    final DentistService dentistService;

    public SpecialtyController(SpecialtyService specialtyService, DentistService dentistService) {
        this.specialtyService = specialtyService;
        this.dentistService = dentistService;
    }

    @PostMapping
    public ResponseEntity<Object> saveSpecialty(@RequestBody @Valid SpecialtyModel specialtyRequest) {
        var specialtyModel = new SpecialtyModel();
        BeanUtils.copyProperties(specialtyRequest, specialtyModel);
        return ResponseEntity.status(HttpStatus.CREATED).body(specialtyService.save(specialtyModel));
    }

    @GetMapping
    public ResponseEntity<List<SpecialtyModel>> getAllSpecialties() {
        return ResponseEntity.status(HttpStatus.OK).body(specialtyService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Object> getSingleSpecialty(@PathVariable(value = "id") UUID id) {
        Optional<SpecialtyModel> specialtyModelOptional = specialtyService.findById(id);
        if (specialtyModelOptional.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Specialty not found.");
        } else {
            return ResponseEntity.status(HttpStatus.OK).body(specialtyModelOptional.get());
        }
    }

    @DeleteMapping
    public ResponseEntity<Object> deleteAllSpecialties() {
        specialtyService.deleteAll();
        return ResponseEntity.status(HttpStatus.OK).body("All specialties were deleted successfully.");
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> deleteSpecialty(@PathVariable(value = "id") UUID id) {
        Optional<SpecialtyModel> specialtyModelOptional = specialtyService.findById(id);
        if (specialtyModelOptional.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Specialty not found.");
        } else {
            specialtyService.delete(specialtyModelOptional.get());
            return ResponseEntity.status(HttpStatus.OK).body("Specialty deleted successfully.");
        }
    }

    @PutMapping("/{specialtyId}/dentist/{dentistId}")
    public ResponseEntity<Object> assignDentistToSpecialty(@PathVariable UUID specialtyId, @PathVariable UUID dentistId) {
        SpecialtyModel specialty = specialtyService.findById(specialtyId).get();
        DentistModel dentist = dentistService.findById(dentistId).get();
        specialty.assignDentist(dentist);
        return ResponseEntity.status(HttpStatus.CREATED).body(specialtyService.save(specialty));
    }
}
