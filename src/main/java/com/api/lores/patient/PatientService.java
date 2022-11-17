package com.api.lores.patient;

import org.springframework.stereotype.Service;
import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class PatientService {

    final PatientRepository patientRepository;
    public PatientService(PatientRepository patientRepository) {
        this.patientRepository = patientRepository;
    }

    // save method
    @Transactional
    public PatientModel save(PatientModel patientModel) {
        return patientRepository.save(patientModel);
    }

    // find by id
    public Optional<PatientModel> findById(UUID id) {
        return patientRepository.findById(id);
    }

    // find all dentists
    public List<PatientModel> findAll() {
        return patientRepository.findAll();
    }

    // delete by id
    @Transactional
    public void delete(PatientModel patientModel) {
        patientRepository.delete(patientModel);
    }

    // delete all
    @Transactional
    public void deleteAll() {
        patientRepository.deleteAll();
    }
}
