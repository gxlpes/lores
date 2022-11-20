package com.api.lores.controller;

import com.api.lores.dto.SpecialtyDto;
import com.api.lores.entity.DentistModel;
import com.api.lores.entity.SpecialtyModel;
import com.api.lores.service.DentistService;
import com.api.lores.service.SpecialtyService;
import org.springframework.beans.BeanUtils;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
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
    @ResponseStatus(HttpStatus.CREATED)
    public SpecialtyModel saveSpecialty(@RequestBody @Valid SpecialtyDto specialtyDto) {
        var specialtyModel = new SpecialtyModel();
        BeanUtils.copyProperties(specialtyDto, specialtyModel);
        return specialtyService.save(specialtyModel);
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<SpecialtyModel> getAllSpecialties() {
        return specialtyService.findAll();
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public SpecialtyModel getSingleSpecialty(@PathVariable UUID id) {
        return specialtyService.findOrFail(id);
    }

    @DeleteMapping
    @ResponseStatus(HttpStatus.OK)
    public String deleteAllSpecialties() {
        specialtyService.deleteAll();
        return "All specialties were deleted successfully.";
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void deleteSpecialty(@PathVariable UUID id) {
        specialtyService.deleteById(id);
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.CREATED)
    public SpecialtyModel updateSpecialty(@PathVariable UUID specialtyId, @RequestBody SpecialtyModel specialty) {
        SpecialtyModel actualSpecialty = specialtyService.findOrFail(specialtyId);
        BeanUtils.copyProperties(specialty, actualSpecialty, "id");
        return specialtyService.save(specialty);
    }

    @PutMapping("/{specialtyId}/dentist/{dentistId}")
    @ResponseStatus(HttpStatus.CREATED)
    public SpecialtyModel assignDentistToSpecialty(@PathVariable UUID specialtyId, @PathVariable UUID dentistId) {
        SpecialtyModel specialty = specialtyService.findOrFail(specialtyId);
        DentistModel dentist = dentistService.findOrFail(dentistId);
        specialty.assignDentist(dentist);
        return specialtyService.save(specialty);
    }
}