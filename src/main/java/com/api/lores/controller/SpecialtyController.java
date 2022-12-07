package com.api.lores.controller;

import com.api.lores.dto.SpecialtyDto;
import com.api.lores.exception.NotFoundException;
import com.api.lores.service.specialty.SpecialtyService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.UUID;

@CrossOrigin(
        allowCredentials = "true",
        origins = "http://localhost:3000",
        allowedHeaders = "*")
@RestController
@RequestMapping("/specialties")
public class SpecialtyController {

    final SpecialtyService specialtyService;

    public SpecialtyController(SpecialtyService specialtyService) {
        this.specialtyService = specialtyService;
    }

    @PostMapping
    @PreAuthorize("hasAnyRole('ROLE_USER', 'ROLE_ADMIN')")
    public ResponseEntity<Object> saveSpecialty(@RequestBody @Valid SpecialtyDto specialtyDto) {
        return specialtyService.save(specialtyDto);
    }

    @GetMapping
    @PreAuthorize("hasAnyRole('ROLE_USER', 'ROLE_ADMIN')")
    public ResponseEntity<Object> getAllSpecialties() throws NotFoundException {
        return specialtyService.findAll();
    }

    @GetMapping("/{id}")
    public SpecialtyDto getSingleSpecialty(@PathVariable UUID id) throws NotFoundException {
        return specialtyService.findById(id);
    }

    @DeleteMapping
    public ResponseEntity<String> deleteAllSpecialties() throws NotFoundException {
        return specialtyService.deleteAll();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> deleteSpecialty(@PathVariable UUID id) throws NotFoundException {
        return specialtyService.deleteById(id);
    }

    @PutMapping("/{id}")
    public ResponseEntity<String> updateSpecialty(@PathVariable UUID id, @RequestBody @Valid SpecialtyDto specialtyDto) throws NotFoundException {
        return specialtyService.update(id, specialtyDto);
    }

    @PutMapping("/{specialtyId}/dentist/{dentistId}")
    public ResponseEntity<Object> assignDentistToSpecialty(@PathVariable UUID specialtyId, @PathVariable UUID dentistId) throws NotFoundException {
        return specialtyService.assignDentistToSpecialty(specialtyId, dentistId);
    }
}