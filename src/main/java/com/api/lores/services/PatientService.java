package com.api.lores.services;

import com.api.lores.entity.PatientModel;
import com.api.lores.exception.EntityNotFound;
import com.api.lores.repository.PatientRepository;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
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
    public PatientModel save(PatientModel patientModel) {
        LOGGER.info(PATIENT + " was saved");
        return patientRepository.save(patientModel);
    }

    public PatientModel findOrFail(UUID id) {
        return patientRepository.findById(id).orElseThrow(() -> {
            LOGGER.error("Error trying to find" + PATIENT);
            throw new EntityNotFound(String.format(PATIENT + "does not exist with the %s", id));
        });
    }

    public List<PatientModel> findAll() {
        return patientRepository.findAll();
    }

    @Transactional
    public void deleteById(UUID id) {
        try {
            LOGGER.info(PATIENT + "was deleted");
            patientRepository.deleteById(id);
        } catch (EmptyResultDataAccessException e) {
            LOGGER.error("Error trying to delete a " + PATIENT);

            throw new EntityNotFound(PATIENT + "do not exist");
        }
    }

    @Transactional
    public void deleteAll() {
        LOGGER.info("Deleting all " + PATIENT);
        patientRepository.deleteAll();
    }
}