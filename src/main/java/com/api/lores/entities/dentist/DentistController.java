package com.api.lores.entities.dentist;

import com.api.lores.embedded.PersonDto;
import org.springframework.beans.BeanUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
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
    public ResponseEntity<Object> saveDentist(@RequestBody @Valid DentistDto dentistDto) {

        if (dentistService.existsByCroNumber(dentistDto.getCroNumber())) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body("Conflict: CRO number is already in use.");
        }

        var dentistModel = new DentistModel();
        BeanUtils.copyProperties(dentistDto, dentistModel);
        return ResponseEntity.status(HttpStatus.CREATED).body(dentistService.save(dentistModel));
    }

    @GetMapping
    public ResponseEntity<List<DentistModel>> getAllDentists() {
        return ResponseEntity.status(HttpStatus.OK).body(dentistService.findAll());
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
        dentistService.deleteAll();
        return ResponseEntity.status(HttpStatus.OK).body("All dentists were deleted successfully.");
    }

    @PutMapping("/{id}")
    public ResponseEntity<Object> updateDentist(@PathVariable(value = "id") UUID id, @RequestBody @Valid DentistDto dentistDto) {
        Optional<DentistModel> dentistModelOptional = dentistService.findById(id);
        if (dentistModelOptional.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Dentist not found");
        } else {
            var dentistModel = new DentistModel();
            BeanUtils.copyProperties(dentistDto, dentistModel);
            return ResponseEntity.status(HttpStatus.OK).body(dentistService.save(dentistModel));
        }

    }
}