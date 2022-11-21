package com.api.lores.controller;

import com.api.lores.dto.SpecialtyDto;
import com.api.lores.entity.SpecialtyModel;
import com.api.lores.service.SpecialtyService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.UUID;

@CrossOrigin
@RestController
@RequestMapping("/specialties")
public class SpecialtyController {

    final SpecialtyService specialtyService;

    public SpecialtyController(SpecialtyService specialtyService) {
        this.specialtyService = specialtyService;
    }

    @PostMapping
    public ResponseEntity<Object> saveSpecialty(@RequestBody @Valid SpecialtyDto specialtyDto) {
        return specialtyService.save(specialtyDto);
    }

    @GetMapping
    public ResponseEntity<Object> getAllSpecialties() {
        return specialtyService.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Object> getSingleSpecialty(@PathVariable UUID id) {
        return specialtyService.findById(id);
    }

    @DeleteMapping
    public ResponseEntity<String> deleteAllSpecialties() {
        return specialtyService.deleteAll();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> deleteSpecialty(@PathVariable UUID id) {
        return specialtyService.deleteById(id);
    }

    @PutMapping("/{id}")
    public ResponseEntity<SpecialtyModel> updateSpecialty(@PathVariable UUID id, @RequestBody @Valid SpecialtyDto specialtyDto) {
        return specialtyService.update(id, specialtyDto);
    }

    @PutMapping("/{specialtyId}/dentist/{dentistId}")
    public ResponseEntity<Object> assignDentistToSpecialty(@PathVariable UUID specialtyId, @PathVariable UUID dentistId) {
        return specialtyService.assignDentistToSpecialty(specialtyId, dentistId);
    }
}