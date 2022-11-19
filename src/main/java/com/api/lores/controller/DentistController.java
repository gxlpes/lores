package com.api.lores.controller;

import com.api.lores.dto.DentistDto;
import com.api.lores.entity.DentistModel;
import com.api.lores.services.DentistService;
import org.springframework.beans.BeanUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
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
    @ResponseStatus(HttpStatus.CREATED)
    public DentistModel saveSpecialty(@RequestBody @Valid DentistDto dentistDto) {
        var dentistModel = new DentistModel();
        BeanUtils.copyProperties(dentistDto, dentistModel);
        return dentistService.save(dentistModel);
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<List<DentistModel>> getAllDentists() {
        return ResponseEntity.status(HttpStatus.OK).body(dentistService.findAll());
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public DentistModel getSingleDentist(@PathVariable UUID id) {
        return dentistService.findOrFail(id);
    }

    @DeleteMapping
    @ResponseStatus(HttpStatus.OK)
    public String deleteAllDentists() {
        dentistService.deleteAll();
        return "All dentists were deleted successfully.";
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void deleteDentist(@PathVariable UUID id) {
        dentistService.deleteById(id);
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.CREATED)
    public DentistModel assignDentistToSpecialty(@PathVariable UUID id, @RequestBody DentistModel dentist) {
        DentistModel actualSpecialty = dentistService.findOrFail(id);
        BeanUtils.copyProperties(dentist, actualSpecialty, "id");
        return dentistService.save(dentist);
    }
}
