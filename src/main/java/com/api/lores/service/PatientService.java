package com.api.lores.service;

import com.api.lores.dto.PatientDto;
import com.api.lores.entity.PatientModel;
import com.api.lores.exception.EntityNotFound;
import com.api.lores.exception.NotFoundException;
import com.api.lores.repository.PatientRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.UUID;

import static com.api.lores.log.Logging.LOGGER;

@Service
public class PatientService {
    public static final String PATIENT = "Patient";
    final PatientRepository patientRepository;

    public PatientService(PatientRepository patientRepository) {
        this.patientRepository = patientRepository;
    }

    @Transactional
    public ResponseEntity<Object> save(PatientDto patientDto) {
        var patientModel = convertDtoToModel(patientDto);
        String person = patientModel.getPerson().getCpfNumber();

        var exists = patientRepository.existsByPersonCpfNumber(person);
        if (exists) return ResponseEntity.status(HttpStatus.CONFLICT).body("CPF number needs to be unique");

        LOGGER.info(PATIENT + " was saved");
        return ResponseEntity.status(HttpStatus.CREATED).body(patientRepository.save(patientModel));
    }

    public PatientModel findOrFail(UUID id) {
        return patientRepository.findById(id).orElseThrow(() -> {
            LOGGER.error("Error trying to find" + PATIENT);
            throw new EntityNotFound(String.format(PATIENT + "does not exist with the %s", id));
        });
    }

    public ResponseEntity<Object> findById(UUID id) {
        var patientFound = findOrFail(id);
        return ResponseEntity.status(HttpStatus.OK).body(patientFound);
    }

    public ResponseEntity<Object> findAll() {
        var patientModelList = patientRepository.findAll();
        if (patientModelList.size() == 0) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No patients were submitted");
        } else {
            return ResponseEntity.status(HttpStatus.OK).body(patientModelList);
        }
    }

    @Transactional
    public ResponseEntity<Object> deleteById(UUID id) {
        try {
            LOGGER.info(PATIENT + " was deleted");
            patientRepository.deleteById(id);
            return ResponseEntity.status(HttpStatus.OK).body("Patient was deleted");
        } catch (EmptyResultDataAccessException e) {
            LOGGER.error("Error trying to delete a " + PATIENT);
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No patients were submitted");
        }
    }

    @Transactional
    public ResponseEntity<String> deleteAll() {
        patientRepository.deleteAll();
        LOGGER.info("Deleting all " + PATIENT);
        return ResponseEntity.status(HttpStatus.OK).body("All patients were deleted");
    }

    @Transactional
    public ResponseEntity<String> updatePatient(UUID id, PatientDto patientDto) {
        PatientModel patientToEdit = findOrFail(id);
        var patientModel = convertDtoToModel(patientDto);
        patientModel.setId(patientToEdit.getId());
        patientRepository.save(patientModel);
        return ResponseEntity.status(HttpStatus.OK).body("Patient was updated");
    }

    public PatientModel convertDtoToModel(PatientDto patientDto) {
        var patientModel = new PatientModel();
        BeanUtils.copyProperties(patientDto, patientModel);
        return patientModel;
    }
}