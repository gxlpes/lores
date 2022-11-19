package com.api.lores.entities.patient;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
@CrossOrigin
@RequestMapping("/patients")
public class PatientController {

    final PatientService patientService;

    Logger logger = LoggerFactory.getLogger(PatientController.class);

    public PatientController(PatientService patientService) {
        this.patientService = patientService;
    }

    @PostMapping
    public ResponseEntity<Object> savePatient(@RequestBody PatientModel patientRequest) {
        logger.trace("Patient saved");
        var patientModel = new PatientModel();
        BeanUtils.copyProperties(patientRequest, patientModel);
        return ResponseEntity.status(HttpStatus.CREATED).body(patientService.save(patientModel));
    }

    @GetMapping
    public ResponseEntity<List<PatientModel>> getAllPatients() {
        return ResponseEntity.status(HttpStatus.OK).body(patientService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Object> getSinglePatient(@PathVariable(value = "id") UUID id) {
        Optional<PatientModel> patientModelOptional = patientService.findById(id);
        if (patientModelOptional.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Patient not found.");
        } else {
            return ResponseEntity.status(HttpStatus.OK).body(patientModelOptional.get());
        }
    }

    @DeleteMapping
    public ResponseEntity<Object> deleteAllPatients() {
        patientService.deleteAll();
        return ResponseEntity.status(HttpStatus.OK).body("All patients were deleted successfully.");
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> deletePatient(@PathVariable(value = "id") UUID id) {
        Optional<PatientModel> patientModelOptional = patientService.findById(id);
        if (patientModelOptional.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Patient not found.");
        } else {
            patientService.delete(patientModelOptional.get());
            return ResponseEntity.status(HttpStatus.OK).body("Patient deleted successfully.");
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<Object> updatePatient(@PathVariable(value = "id") UUID id, @RequestBody PatientModel patient) {
        Optional<PatientModel> patientModelOptional = patientService.findById(id);
        if (patientModelOptional.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Patient not found");
        } else {
            var patientModel = new PatientModel();
            BeanUtils.copyProperties(patient, patientModel);
            return ResponseEntity.status(HttpStatus.OK).body(patientService.save(patientModel));
        }
    }
}