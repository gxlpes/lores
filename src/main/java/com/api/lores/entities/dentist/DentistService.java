package com.api.lores.entities.dentist;

import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class DentistService {

    final DentistRepository dentistRepository;
    public DentistService(DentistRepository dentistRepository) {
        this.dentistRepository = dentistRepository;
    }

    // save method
    @Transactional
    public DentistModel save(DentistModel dentistModel) {
        return dentistRepository.save(dentistModel);
    }

    // find by id
    public Optional<DentistModel> findById(UUID id) {
        return dentistRepository.findById(id);
    }

    // find all dentists
    public List<DentistModel> findAll() {
        return dentistRepository.findAll();
    }

    // delete by id
    @Transactional
    public void delete(DentistModel dentistModel) {
        dentistRepository.delete(dentistModel);
    }

    // delete all
    @Transactional
    public void deleteAll() {
        dentistRepository.deleteAll();
    }

    // check if exists by cro
    public boolean existsByCroNumber(String croNumber) {
        return dentistRepository.existsByCroNumber(croNumber);
    }
}
