package com.api.lores.services;

import com.api.lores.entity.SpecialtyModel;
import com.api.lores.repository.SpecialtyRepository;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class SpecialtyService {
    final SpecialtyRepository specialtyRepository;

    public SpecialtyService(SpecialtyRepository specialtyRepository) {
        this.specialtyRepository = specialtyRepository;
    }

    // save method
    @Transactional
    public SpecialtyModel save(SpecialtyModel specialtyModel) {
        return specialtyRepository.save(specialtyModel);
    }

    // find by id
    public Optional<SpecialtyModel> findById(UUID id) {
        return specialtyRepository.findById(id);
    }

    // find all dentists
    public List<SpecialtyModel> findAll() {
        return specialtyRepository.findAll();
    }

    // delete by id
    @Transactional
    public void delete(SpecialtyModel dentistModel) {
        specialtyRepository.delete(dentistModel);
    }

    // delete all
    @Transactional
    public void deleteAll() {
        specialtyRepository.deleteAll();
    }
}
