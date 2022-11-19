package com.api.lores.services;

import com.api.lores.entity.TreatmentModel;
import com.api.lores.repository.TreatmentRepository;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class TreatmentService {
    final TreatmentRepository treatmentRepository;

    public TreatmentService(TreatmentRepository treatmentRepository) {
        this.treatmentRepository = treatmentRepository;
    }

    // save method
    @Transactional
    public TreatmentModel save(TreatmentModel specialtyModel) {
        return treatmentRepository.save(specialtyModel);
    }

    // find by id
    public Optional<TreatmentModel> findById(UUID id) {
        return treatmentRepository.findById(id);
    }

    // find all dentists
    public List<TreatmentModel> findAll() {
        return treatmentRepository.findAll();
    }

    // delete by id
    @Transactional
    public void delete(TreatmentModel treatmentModel) {
        treatmentRepository.delete(treatmentModel);
    }

    // delete all
    @Transactional
    public void deleteAll() {
        treatmentRepository.deleteAll();
    }
}

