package com.api.lores.service;

import com.api.lores.entity.SpecialtyModel;
import com.api.lores.exception.EntityNotFound;
import com.api.lores.repository.SpecialtyRepository;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.UUID;

import static com.api.lores.log.Logging.LOGGER;

@Service
public class SpecialtyService {

    public static final String SPECIALTY_DOES_NOT_EXIST = "Specialty with %s does not exist.";
    public static final String SPECIALTY = "Specialty";
    final SpecialtyRepository specialtyRepository;

    public SpecialtyService(SpecialtyRepository specialtyRepository) {
        this.specialtyRepository = specialtyRepository;
    }

    @Transactional
    public SpecialtyModel save(SpecialtyModel specialtyModel) {
        LOGGER.info(SPECIALTY + " was saved");
        return specialtyRepository.save(specialtyModel);
    }

    public SpecialtyModel findOrFail(UUID id) {
        return specialtyRepository.findById(id).orElseThrow(() -> {
            LOGGER.error("Error trying to find" + SPECIALTY);
            throw new EntityNotFound(String.format(SPECIALTY_DOES_NOT_EXIST, id));
        });
    }

    public List<SpecialtyModel> findAll() {
        return specialtyRepository.findAll();
    }

    @Transactional
    public void deleteById(UUID id) {
        try {
            LOGGER.info(SPECIALTY + " was deleted");
            specialtyRepository.deleteById(id);
        } catch (EmptyResultDataAccessException e) {
            LOGGER.error("Error trying to delete an entity");
            throw new EntityNotFound(SPECIALTY + " do not exist");
        }
    }

    @Transactional
    public void deleteAll() {
        LOGGER.info("Deleting all " + SPECIALTY);
        specialtyRepository.deleteAll();
    }

}

