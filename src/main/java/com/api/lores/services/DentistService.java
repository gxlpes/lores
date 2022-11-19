package com.api.lores.services;

import com.api.lores.entity.DentistModel;
import com.api.lores.exception.EntityNotFound;
import com.api.lores.repository.DentistRepository;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.UUID;

import static com.api.lores.log.Logging.LOGGER;

@Service
public class DentistService {

    public static final String DENTIST = "Dentist";
    final DentistRepository dentistRepository;

    public DentistService(DentistRepository dentistRepository) {
        this.dentistRepository = dentistRepository;
    }

    @Transactional
    public DentistModel save(DentistModel dentistModel) {
        LOGGER.info(DENTIST + " was saved");
        return dentistRepository.save(dentistModel);
    }

    public DentistModel findOrFail(UUID id) {
        return dentistRepository.findById(id).orElseThrow(() -> {
            LOGGER.error("Error trying to find " + DENTIST);
            throw new EntityNotFound(String.format("Entity does not exist with the %s", id));
        });
    }

    public List<DentistModel> findAll() {
        return dentistRepository.findAll();
    }

    @Transactional
    public void deleteById(UUID id) {
        try {
            LOGGER.info(DENTIST + " was deleted");
            dentistRepository.deleteById(id);
        } catch (EmptyResultDataAccessException e) {
            LOGGER.error("Error trying to delete a " + DENTIST);
            throw new EntityNotFound("Entity do not exist");
        }
    }

    @Transactional
    public void deleteAll() {
        LOGGER.info("Deleting all " + DENTIST);
        dentistRepository.deleteAll();
    }

    public boolean existsByCroNumber(String croNumber) {
        return dentistRepository.existsByCroNumber(croNumber);
    }
}