package com.api.lores.controller;

import com.api.lores.dto.DentistDto;
import com.api.lores.exception.NotFoundException;
import com.api.lores.service.dentist.DentistService;
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
@RequestMapping("/dentists")
public class DentistController {

    private final DentistService dentistService;

    public DentistController(DentistService dentistService) {
        this.dentistService = dentistService;
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @PostMapping
    public ResponseEntity<Object> saveDentist(@RequestBody @Valid DentistDto dentistDto) {
        return dentistService.save(dentistDto);
    }

    @GetMapping
    @PreAuthorize("hasAnyRole('ROLE_USER', 'ROLE_ADMIN')")
    public ResponseEntity<Object> getAllDentists() throws NotFoundException {
        return dentistService.findAll();
    }

    @GetMapping("/{id}")
    public DentistDto getSingleDentist(@PathVariable UUID id) throws NotFoundException {
        return dentistService.findById(id);
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @DeleteMapping
    public ResponseEntity<String> deleteAllDentists() throws NotFoundException {
        return dentistService.deleteAll();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> deleteDentist(@PathVariable UUID id) throws NotFoundException {
        return dentistService.deleteById(id);
    }

    @PutMapping("/{id}")
    public ResponseEntity<String> updateDentist(@PathVariable UUID id, @RequestBody @Valid DentistDto dentistDto) throws NotFoundException {
        return dentistService.update(id, dentistDto);
    }
}
