package com.api.lores.controller;

import com.api.lores.dto.DentistDto;
import com.api.lores.service.DentistService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.UUID;

@CrossOrigin
@RestController
@RequestMapping("/dentists")
public class DentistController {

    final DentistService dentistService;

    public DentistController(DentistService dentistService) {
        this.dentistService = dentistService;
    }

    @PostMapping
    public ResponseEntity<Object> saveDentist(@RequestBody @Valid DentistDto dentistDto) {
        return dentistService.save(dentistDto);
    }

    @GetMapping
    public ResponseEntity<Object> getAllDentists() {
        return dentistService.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Object> getSingleDentist(@PathVariable UUID id) {
        return dentistService.findById(id);
    }

    @DeleteMapping
    public ResponseEntity<String> deleteAllDentists() {
        return dentistService.deleteAll();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> deleteDentist(@PathVariable UUID id) {
        return dentistService.deleteById(id);
    }

    @PutMapping("/{id}")
    public ResponseEntity<String> updateDentist(@PathVariable UUID id, @RequestBody @Valid DentistDto dentistDto) {
        return dentistService.updateDentist(id, dentistDto);
    }
}
