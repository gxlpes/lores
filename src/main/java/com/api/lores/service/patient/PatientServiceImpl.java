package com.api.lores.service.patient;

import com.api.lores.dto.PatientDto;
import com.api.lores.exception.EntityNotFound;
import com.api.lores.exception.NotFoundException;
import com.api.lores.mapper.PatientMapper;
import com.api.lores.model.PatientModel;
import com.api.lores.repository.PatientRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.UUID;

import static com.api.lores.log.Logging.LOGGER;

@Service
public class PatientServiceImpl implements PatientService {

    public static final String PATIENT = "patient";
    private final PatientRepository patientRepository;
    private final PatientMapper patientMapper = PatientMapper.INSTANCE;

    public PatientServiceImpl(PatientRepository patientRepository) {
        this.patientRepository = patientRepository;
    }

    @Override
    @Transactional
    public ResponseEntity<Object> save(PatientDto dto) {
        var exists = patientRepository.existsByPersonCpfNumber(dto.getPerson().getCpfNumber());
        if (exists) return ResponseEntity.status(HttpStatus.CONFLICT).body("CPF number needs to be unique");
        var patientToSave = patientMapper.toModel(dto);
        patientRepository.save(patientToSave);
        LOGGER.info("Saved a " + PATIENT);
        return ResponseEntity.status(HttpStatus.OK).body(patientToSave);
    }

    @Override
    public PatientDto findById(UUID id) throws NotFoundException {
        var patient = patientRepository.findById(id).orElseThrow(() -> new NotFoundException());
        return patientMapper.toDto(patient);
    }

    @Override
    public ResponseEntity<Object> findAll() throws NotFoundException {
        var patientList = patientRepository.findAll();
        if (patientList.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No patients were found");
        } else {
            return ResponseEntity.status(HttpStatus.OK).body(patientList);
        }
    }

    @Override
    public ResponseEntity<String> update(UUID id, PatientDto dto) throws NotFoundException {
        patientRepository.findById(id).orElseThrow(() -> new NotFoundException());
        var patientToSave = patientMapper.toModel(dto);
        patientToSave.setId(id);
        patientRepository.save(patientToSave);
        LOGGER.info("Updated a " + PATIENT);
        return ResponseEntity.status(HttpStatus.OK).body("A " + PATIENT + " was updated");
    }

    @Override
    public ResponseEntity<String> deleteAll() throws NotFoundException {
        patientRepository.deleteAll();
        LOGGER.info("All " + PATIENT + "s were deleted.");
        return ResponseEntity.status(HttpStatus.OK).body("All " + PATIENT + "s were deleted");
    }

    @Override
    public ResponseEntity<Object> deleteById(UUID id) throws NotFoundException {
        var patient = patientRepository.findById(id).orElseThrow(() -> new NotFoundException());
        patientRepository.delete(patient);
        LOGGER.info("A " + PATIENT + " was deleted with the ID " + id + " .");
        return ResponseEntity.status(HttpStatus.OK).body("A " + PATIENT + " was deleted");
    }

    @Override
    public PatientModel findOrFail(UUID id) {
        return patientRepository.findById(id).orElseThrow(() -> {
            LOGGER.error("Error trying to find " + PATIENT);
            throw new EntityNotFound(String.format("Entity does not exist with the %s", id));
        });
    }

}