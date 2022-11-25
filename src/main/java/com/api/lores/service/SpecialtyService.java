package com.api.lores.service;

import com.api.lores.dto.SpecialtyDto;
import com.api.lores.entity.DentistModel;
import com.api.lores.entity.SpecialtyModel;
import com.api.lores.exception.EntityNotFound;
import com.api.lores.repository.SpecialtyRepository;
import com.api.lores.service.dentist.DentistService;
import org.springframework.beans.BeanUtils;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.UUID;

import static com.api.lores.log.Logging.LOGGER;

@Service
public class SpecialtyService {

    public static final String SPECIALTY_DOES_NOT_EXIST = "Specialty with %s does not exist.";
    public static final String SPECIALTY = "Specialty";
    final SpecialtyRepository specialtyRepository;
    final DentistService dentistService;

    public SpecialtyService(SpecialtyRepository specialtyRepository, DentistService dentistService) {
        this.specialtyRepository = specialtyRepository;
        this.dentistService = dentistService;
    }

    @Transactional
    public ResponseEntity<Object> save(SpecialtyDto specialtyDto) {
        var specialtyModel = convertDtoToModel(specialtyDto);
        LOGGER.info(SPECIALTY + " was saved");
        return ResponseEntity.status(HttpStatus.CREATED).body(specialtyRepository.save(specialtyModel));
    }

    public SpecialtyModel findOrFail(UUID id) {
        return specialtyRepository.findById(id).orElseThrow(() -> {
            LOGGER.error("Error trying to find" + SPECIALTY);
            throw new EntityNotFound(String.format(SPECIALTY_DOES_NOT_EXIST, id));
        });
    }

    public ResponseEntity<Object> findById(UUID id) {
        var patientFound = findOrFail(id);
        return ResponseEntity.status(HttpStatus.OK).body(patientFound);
    }

    public ResponseEntity<Object> findAll() {
        var specialtyModelList = specialtyRepository.findAll();
        if (specialtyModelList.size() == 0) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No specialties were submitted");
        } else {
            return ResponseEntity.status(HttpStatus.OK).body(specialtyModelList);
        }
    }

    @Transactional
    public ResponseEntity<Object> deleteById(UUID id) {
        try {
            LOGGER.info(SPECIALTY + " was deleted");
            specialtyRepository.deleteById(id);
            return ResponseEntity.status(HttpStatus.OK).body("Specialty was deleted");
        } catch (EmptyResultDataAccessException e) {
            LOGGER.error("Error trying to delete a " + SPECIALTY);
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No specialties were submitted");
        }
    }

    @Transactional
    public ResponseEntity<String> deleteAll() {
        specialtyRepository.deleteAll();
        LOGGER.info("Deleting all " + SPECIALTY);
        return ResponseEntity.status(HttpStatus.OK).body("All specialties were deleted");
    }

    @Transactional
    public ResponseEntity<SpecialtyModel> update(UUID id, SpecialtyDto specialtyDto) {
        SpecialtyModel specialtyModelToEdit = findOrFail(id);
        var specialtyModel = new SpecialtyModel();
        BeanUtils.copyProperties(specialtyDto, specialtyModel);
        specialtyModel.setId(specialtyModelToEdit.getId());
        return ResponseEntity.status(HttpStatus.OK).body(specialtyRepository.save(specialtyModel));
    }

    public ResponseEntity<Object> assignDentistToSpecialty(UUID idSpec, UUID idDent) {
        SpecialtyModel specialty = findOrFail(idSpec);
        DentistModel dentist = dentistService.findOrFail(idDent);
        specialty.assignDentist(dentist);
        specialtyRepository.save(specialty);
        return ResponseEntity.status(HttpStatus.OK).body("Dentist assigned to specialty.");
    }

    public SpecialtyModel convertDtoToModel(SpecialtyDto specialtyDto) {
        var specialtyModel = new SpecialtyModel();
        BeanUtils.copyProperties(specialtyDto, specialtyModel);
        return specialtyModel;
    }

}

