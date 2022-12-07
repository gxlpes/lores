package com.api.lores.service.dentist;

import com.api.lores.dto.DentistDto;
import com.api.lores.exception.EntityNotFound;
import com.api.lores.exception.NotFoundException;
import com.api.lores.mapper.DentistMapper;
import com.api.lores.model.DentistModel;
import com.api.lores.repository.DentistRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.UUID;

import static org.hibernate.tool.schema.SchemaToolingLogging.LOGGER;

@Service
public class DentistServiceImpl implements DentistService {

    public static final String DENTIST = "dentist";
    private final DentistRepository dentistRepository;
    private final DentistMapper dentistMapper = DentistMapper.INSTANCE;

    public DentistServiceImpl(DentistRepository dentistRepository) {
        this.dentistRepository = dentistRepository;
    }

    @Override
    @Transactional
    public ResponseEntity<Object> save(DentistDto dto) {
        var exists = dentistRepository.findByCroNumber(dto.getCroNumber()).isPresent();
        if (exists) return ResponseEntity.status(HttpStatus.CONFLICT).body("CRO number needs to be unique");
        var dentistToSave = dentistMapper.toModel(dto);
        dentistRepository.save(dentistToSave);
        LOGGER.info("Saved a " + DENTIST);
        return ResponseEntity.status(HttpStatus.OK).body(dentistToSave);
    }

    @Override
    public DentistDto findById(UUID id) throws NotFoundException {
        var dentist = dentistRepository.findById(id).orElseThrow(() -> new NotFoundException());
        return dentistMapper.toDto(dentist);
    }

    @Override
    public ResponseEntity<Object> findAll() {
        var dentistList = dentistRepository.findAll();
        if (dentistList.isEmpty()) {
            return ResponseEntity.status(HttpStatus.OK).body("No dentists were found");
        } else {
            return ResponseEntity.status(HttpStatus.OK).body(dentistList);
        }
    }

    @Override
    public ResponseEntity<String> update(UUID id, DentistDto dto) throws NotFoundException {
        dentistRepository.findById(id).orElseThrow(() -> new NotFoundException());
        var dentistToSave = dentistMapper.toModel(dto);
        dentistToSave.setId(id);
        dentistRepository.save(dentistToSave);
        LOGGER.info("Updated a " + DENTIST);
        return ResponseEntity.status(HttpStatus.OK).body("A " + DENTIST + " was updated");
    }

    @Override
    public ResponseEntity<String> deleteAll() throws NotFoundException {
        dentistRepository.deleteAll();
        LOGGER.info("All " + DENTIST + "s were deleted.");
        return ResponseEntity.status(HttpStatus.OK).body("All " + DENTIST + "s were deleted");
    }

    @Override
    public ResponseEntity<Object> deleteById(UUID id) throws NotFoundException {
        var dentist = dentistRepository.findById(id).orElseThrow(() -> new NotFoundException());
        dentistRepository.delete(dentist);
        LOGGER.info("A " + DENTIST + " was deleted with the ID " + id + " .");
        return ResponseEntity.status(HttpStatus.OK).body("A " + DENTIST + " was deleted");
    }

    @Override
    public DentistModel findOrFail(UUID id) {
        return dentistRepository.findById(id).orElseThrow(() -> {
            LOGGER.error("Error trying to find " + DENTIST);
            throw new EntityNotFound(String.format("Entity does not exist with the %s", id));
        });
    }

}
