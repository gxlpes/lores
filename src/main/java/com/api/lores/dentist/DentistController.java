package com.api.lores.dentist;

import org.springframework.beans.BeanUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
@CrossOrigin
@RequestMapping("/dentists")
public class DentistController {
    final DentistService dentistService;

    public DentistController(DentistService dentistService) {
        this.dentistService = dentistService;
    }

    @PostMapping
    public ResponseEntity<Object> saveDentist(@RequestBody DentistModel dentistRequest) {
        var dentistModel = new DentistModel();
        BeanUtils.copyProperties(dentistRequest, dentistModel);
        return ResponseEntity.status(HttpStatus.CREATED).body(dentistService.save(dentistModel));
    }

    @GetMapping("/{id}")
    public ResponseEntity<Object> getSingleDentist(@PathVariable(value = "id") UUID id) {
        Optional<DentistModel> dentistModelOptional = dentistService.findById(id);
        if (dentistModelOptional.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Dentist not found.");
        } else {
            return ResponseEntity.status(HttpStatus.OK).body(dentistModelOptional.get());
        }
    }

    @GetMapping
    public ResponseEntity<List<DentistModel>> getAllDentists() {
        return ResponseEntity.status(HttpStatus.OK).body(dentistService.findAll());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> deleteDentist(@PathVariable(value = "id") UUID id) {
        Optional<DentistModel> dentistModelOptional = dentistService.findById(id);
        if (dentistModelOptional.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Dentist not found.");
        } else {
            dentistService.delete(dentistModelOptional.get());
            return ResponseEntity.status(HttpStatus.OK).body("Dentist deleted successfully.");
        }
    }

    @DeleteMapping
    public ResponseEntity<Object> deleteAllDentists() {
        return ResponseEntity.status(HttpStatus.OK).body("All dentists were deleted successfully.");
    }

    @PutMapping("/{id}")
    public ResponseEntity<Object> updateDentist(@PathVariable(value = "id") UUID id, @RequestBody DentistModel dentist) {
        Optional<DentistModel> dentistModelOptional = dentistService.findById(id);
        if (dentistModelOptional.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Dentist not found");
        } else {
            var dentistModel = new DentistModel();
            BeanUtils.copyProperties(dentist, dentistModel);
            return ResponseEntity.status(HttpStatus.OK).body(dentistService.save(dentistModel));
        }

    }
}