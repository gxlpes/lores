package com.api.lores.entities.patient;

import com.api.lores.generic.GenericRepository;
import com.api.lores.generic.GenericService;
import org.springframework.stereotype.Service;

@Service
public class PatientService extends GenericService<PatientModel> {

    final PatientRepository patientRepository;

    public PatientService(PatientRepository patientRepository) {
        this.patientRepository = patientRepository;
    }

    @Override
    public GenericRepository<PatientModel> getRepository() {
        return patientRepository;
    }
}