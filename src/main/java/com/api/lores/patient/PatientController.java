package com.api.lores.patient;

import com.api.lores.patient.PatientModel;
import com.api.lores.patient.PatientService;
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

    public PatientController(PatientService patientService) {
        this.patientService = patientService;
    }

    @PostMapping
    public ResponseEntity<Object> savePatient(@RequestBody PatientModel patientRequest) {
        var patientModel = new PatientModel();
        BeanUtils.copyProperties(patientRequest, patientModel);
        return ResponseEntity.status(HttpStatus.CREATED).body(patientService.save(patientModel));
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

    @GetMapping
    public ResponseEntity<List<PatientModel>> getAllPatients() {
        return ResponseEntity.status(HttpStatus.OK).body(patientService.findAll());
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

    @DeleteMapping
    public ResponseEntity<Object> deleteAllPatients() {
        return ResponseEntity.status(HttpStatus.OK).body("All patients were deleted successfully.");
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