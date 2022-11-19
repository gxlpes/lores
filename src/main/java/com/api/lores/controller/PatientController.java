package com.api.lores.controller;

import com.api.lores.entity.PatientModel;
import com.api.lores.services.PatientService;
import org.springframework.beans.BeanUtils;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.UUID;

@RestController
@CrossOrigin
@RequestMapping("/patients")
public class PatientController {
    final PatientService patientService;

    public PatientController(PatientService patientService) {
        this.patientService = patientService;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public PatientModel savePatient(@RequestBody @Valid PatientModel patient) {
        var patientModel = new PatientModel();
        BeanUtils.copyProperties(patient, patientModel);
        return patientService.save(patientModel);
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<PatientModel> getAllPatients() {
        return patientService.findAll();
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public PatientModel getSinglePatient(@PathVariable UUID id) {
        return patientService.findOrFail(id);
    }

    @DeleteMapping
    @ResponseStatus(HttpStatus.OK)
    public String deleteAllPatients() {
        patientService.deleteAll();
        return "All patients were deleted successfully.";
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void deleteAllPatients(@PathVariable UUID id) {
        patientService.deleteById(id);
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.CREATED)
    public PatientModel updatePatient(@PathVariable UUID id, @RequestBody PatientModel patient) {
        PatientModel actualPatient = patientService.findOrFail(id);
        BeanUtils.copyProperties(patient, actualPatient, "id");
        return patientService.save(patient);

    }
}