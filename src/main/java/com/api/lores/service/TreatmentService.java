package com.api.lores.service;

import com.api.lores.dto.TreatmentDto;
import com.api.lores.entity.TreatmentModel;
import com.api.lores.exception.EntityNotFound;
import com.api.lores.exception.NotFoundException;
import com.api.lores.repository.TreatmentRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.UUID;

import static com.api.lores.log.Logging.LOGGER;

@Service
public class TreatmentService {
    public static final String TREATMENT = "Treatment";
    final TreatmentRepository treatmentRepository;

    public TreatmentService(TreatmentRepository treatmentRepository) {
        this.treatmentRepository = treatmentRepository;
    }

    @Transactional
    public TreatmentModel save(TreatmentModel treatmentModel) {
        LOGGER.info(TREATMENT + " was saved");
        return treatmentRepository.save(treatmentModel);
    }

    public TreatmentModel findOrFail(UUID id) {
        return treatmentRepository.findById(id).orElseThrow(() -> {
            LOGGER.error("Error trying to find" + TREATMENT);
            throw new EntityNotFound(String.format(TREATMENT + " do not exist with the %s", id));
        });
    }

    public ResponseEntity<Object> findById(UUID id) {
        var treatmentFound = findOrFail(id);
        return ResponseEntity.status(HttpStatus.OK).body(treatmentFound);
    }

    public ResponseEntity<Object> findAll() {
        var treatmentModelList = treatmentRepository.findAll();
        if (treatmentModelList.size() == 0) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No treatments were submitted");
        } else {
            return ResponseEntity.status(HttpStatus.OK).body(treatmentModelList);
        }
    }

    @Transactional
    public ResponseEntity<Object> deleteById(UUID id) {
        try {
            LOGGER.info(TREATMENT + " was deleted");
            treatmentRepository.deleteById(id);
            return ResponseEntity.status(HttpStatus.OK).body("Treatment was deleted");
        } catch (EmptyResultDataAccessException e) {
            LOGGER.error("Error trying to delete a " + TREATMENT);
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No treatments were submitted");
        }
    }

    @Transactional
    public ResponseEntity<String> deleteAll() {
        treatmentRepository.deleteAll();
        LOGGER.info("Deleting all " + TREATMENT);
        return ResponseEntity.status(HttpStatus.OK).body("All treatments were deleted");
    }

    public TreatmentModel convertDtoToModel(TreatmentDto treatmentDto) {
        var treatmentModel = new TreatmentModel();
        BeanUtils.copyProperties(treatmentDto, treatmentModel);
        return treatmentModel;
    }

}

