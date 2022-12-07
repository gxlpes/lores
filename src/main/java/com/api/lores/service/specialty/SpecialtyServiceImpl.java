package com.api.lores.service.specialty;

import com.api.lores.dto.SpecialtyDto;
import com.api.lores.dto.SpecialtyDto;
import com.api.lores.exception.NotFoundException;
import com.api.lores.mapper.SpecialtyMapper;
import com.api.lores.model.DentistModel;
import com.api.lores.model.SpecialtyModel;
import com.api.lores.model.SpecialtyModel;
import com.api.lores.exception.EntityNotFound;
import com.api.lores.repository.SpecialtyRepository;
import com.api.lores.service.dentist.DentistService;
import com.api.lores.service.specialty.SpecialtyService;
import org.springframework.beans.BeanUtils;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.UUID;

import static com.api.lores.log.Logging.LOGGER;

@Service
public class SpecialtyServiceImpl implements SpecialtyService {

    public static final String SPECIALTY_DOES_NOT_EXIST = "Specialty with %s does not exist.";
    public static final String SPECIALTY = "Specialty";

    private final DentistService dentistService;
    private final SpecialtyRepository specialtyRepository;
    private final SpecialtyMapper specialtyMapper = SpecialtyMapper.INSTANCE;


    public SpecialtyServiceImpl(SpecialtyRepository specialtyRepository, DentistService dentistService) {
        this.specialtyRepository = specialtyRepository;
        this.dentistService = dentistService;
    }


    @Override
    @Transactional
    public ResponseEntity<Object> save(SpecialtyDto dto) {
        var specialtyToSave = specialtyMapper.toModel(dto);
        specialtyRepository.save(specialtyToSave);
        LOGGER.info("Saved a " + SPECIALTY);
        return ResponseEntity.status(HttpStatus.OK).body(specialtyToSave);
    }

    @Override
    public SpecialtyDto findById(UUID id) throws NotFoundException {
        var specialty = specialtyRepository.findById(id).orElseThrow(() -> new NotFoundException());
        return specialtyMapper.toDto(specialty);
    }

    @Override
    public ResponseEntity<Object> findAll() throws NotFoundException {
        var specialtyList = specialtyRepository.findAll();
        if (specialtyList.isEmpty()) {
            return ResponseEntity.status(HttpStatus.OK).body("No specialtys were found");
        } else {
            return ResponseEntity.status(HttpStatus.OK).body(specialtyList);
        }
    }

    @Override
    public ResponseEntity<String> update(UUID id, SpecialtyDto dto) throws NotFoundException {
        specialtyRepository.findById(id).orElseThrow(() -> new NotFoundException());
        var specialtyToSave = specialtyMapper.toModel(dto);
        specialtyToSave.setId(id);
        specialtyRepository.save(specialtyToSave);
        LOGGER.info("Updated a " + SPECIALTY);
        return ResponseEntity.status(HttpStatus.OK).body("A " + SPECIALTY + " was updated");
    }

    @Override
    public ResponseEntity<String> deleteAll() throws NotFoundException {
        specialtyRepository.deleteAll();
        LOGGER.info("All " + SPECIALTY + "s were deleted.");
        return ResponseEntity.status(HttpStatus.OK).body("All " + SPECIALTY + "s were deleted");
    }

    @Override
    public ResponseEntity<Object> deleteById(UUID id) throws NotFoundException {
        var specialty = specialtyRepository.findById(id).orElseThrow(() -> new NotFoundException());
        specialtyRepository.delete(specialty);
        LOGGER.info("A " + SPECIALTY + " was deleted with the ID " + id + " .");
        return ResponseEntity.status(HttpStatus.OK).body("A " + SPECIALTY + " was deleted");
    }

    @Override
    public SpecialtyModel findOrFail(UUID id) {
        return specialtyRepository.findById(id).orElseThrow(() -> {
            LOGGER.error("Error trying to find " + SPECIALTY);
            throw new EntityNotFound(String.format("Entity does not exist with the %s", id));
        });
    }

    public ResponseEntity<Object> assignDentistToSpecialty(UUID idSpec, UUID idDent) {
        SpecialtyModel specialty = findOrFail(idSpec);
        DentistModel dentist = dentistService.findOrFail(idDent);
        specialty.assignDentist(dentist);
        specialtyRepository.save(specialty);
        return ResponseEntity.status(HttpStatus.OK).body("Dentist assigned to specialty.");
    }

}

