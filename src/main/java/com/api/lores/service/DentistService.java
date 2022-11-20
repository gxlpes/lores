package com.api.lores.service;

import com.api.lores.dto.DentistDto;
import com.api.lores.entity.DentistModel;
import com.api.lores.exception.EntityNotFound;
import com.api.lores.repository.DentistRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
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
    public ResponseEntity<Object> save(DentistDto dentistDto) {
        var dentistModel = convertDtoToModel(dentistDto);
        var exists = dentistRepository.existsByCroNumber(dentistModel.getCroNumber());
        if (exists) return ResponseEntity.status(HttpStatus.CONFLICT).body("CRO number needs to be unique");

        LOGGER.info(DENTIST + " was saved");
        return ResponseEntity.status(HttpStatus.CREATED).body(dentistRepository.save(dentistModel));
    }

    public DentistModel findOrFail(UUID id) {
        return dentistRepository.findById(id).orElseThrow(() -> {
            LOGGER.error("Error trying to find " + DENTIST);
            throw new EntityNotFound(String.format("Entity does not exist with the %s", id));
        });
    }

    public ResponseEntity<Object> findById(UUID id) {
        var dentistFound = findOrFail(id);
        return ResponseEntity.status(HttpStatus.OK).body(dentistFound);
    }

    public ResponseEntity<Object> findAll() {
        var dentistModelList = dentistRepository.findAll();
        if (dentistModelList.size() == 0) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No dentists were submitted");
        } else {
            return ResponseEntity.status(HttpStatus.OK).body(dentistModelList);
        }
    }

    @Transactional
    public ResponseEntity<Object> deleteById(UUID id) {
        try {
            LOGGER.info(DENTIST + " was deleted");
            dentistRepository.deleteById(id);
            return ResponseEntity.status(HttpStatus.OK).body("Dentist was deleted");
        } catch (EmptyResultDataAccessException e) {
            LOGGER.error("Error trying to delete a " + DENTIST);
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No dentists were submitted");
        }
    }

    @Transactional
    public ResponseEntity<String> deleteAll() {
        dentistRepository.deleteAll();
        LOGGER.info("Deleting all " + DENTIST);
        return ResponseEntity.status(HttpStatus.OK).body("All dentists were deleted");
    }

    @Transactional
    public void updateDentist(UUID id, DentistDto dentistDto) {
        DentistModel dentistToEdit = findOrFail(id);
        var dentistModel = convertDtoToModel(dentistDto);
        dentistModel.setId(dentistToEdit.getId());
        dentistRepository.save(dentistModel);
    }

    public DentistModel convertDtoToModel(DentistDto dentistDto) {
        var dentistModel = new DentistModel();
        BeanUtils.copyProperties(dentistDto, dentistModel);
        return dentistModel;
    }
}