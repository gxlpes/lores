package com.api.lores.service;

import com.api.lores.entity.TreatmentModel;
import com.api.lores.exception.EntityNotFound;
import com.api.lores.repository.TreatmentRepository;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
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
    public TreatmentModel save(TreatmentModel specialtyModel) {
        LOGGER.info(TREATMENT + " was saved");
        return treatmentRepository.save(specialtyModel);
    }

    public TreatmentModel findOrFail(UUID id) {
        return treatmentRepository.findById(id).orElseThrow(() -> {
            LOGGER.error("Error trying to find" + TREATMENT);
            throw new EntityNotFound(String.format(TREATMENT + " do not exist with the %s", id));
        });
    }

    public List<TreatmentModel> findAll() {
        return treatmentRepository.findAll();
    }

    @Transactional
    public void deleteById(UUID id) {
        try {
            LOGGER.info(TREATMENT + "was created");
            treatmentRepository.deleteById(id);
        } catch (EmptyResultDataAccessException e) {
            throw new EntityNotFound(TREATMENT + " do not exist");
        }
    }

    @Transactional
    public void deleteAll() {
        LOGGER.info("Deleting all " + TREATMENT);
        treatmentRepository.deleteAll();
    }

}

