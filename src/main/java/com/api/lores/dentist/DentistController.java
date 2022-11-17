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
    public ResponseEntity<Object> deleteParkingSpot(@PathVariable(value = "id") UUID id) {
        Optional<DentistModel> parkingSpotModelOptional = dentistService.findById(id);
        if (parkingSpotModelOptional.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Dentist not found.");
        } else {
            dentistService.delete(parkingSpotModelOptional.get());
            return ResponseEntity.status(HttpStatus.OK).body("Dentist deleted successfully.");
        }
    }
}