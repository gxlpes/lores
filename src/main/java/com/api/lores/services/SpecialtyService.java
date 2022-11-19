package com.api.lores.services;

import com.api.lores.entity.SpecialtyModel;
import com.api.lores.exception.EntityNotFound;
import com.api.lores.repository.SpecialtyRepository;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.UUID;

@Service
public class SpecialtyService {
    public static final String SPECIALTY_DOES_NOT_EXIST = "Specialty with %s does not exist.";
    final SpecialtyRepository specialtyRepository;

    public SpecialtyService(SpecialtyRepository specialtyRepository) {
        this.specialtyRepository = specialtyRepository;
    }

    // save method
    @Transactional
    public SpecialtyModel save(SpecialtyModel specialtyModel) {
        return specialtyRepository.save(specialtyModel);
    }

    // find by id or send exception
    public SpecialtyModel findOrFail(UUID id) {
        return specialtyRepository.findById(id).orElseThrow(() -> new EntityNotFound(String.format(SPECIALTY_DOES_NOT_EXIST, id)));
    }

    // find all specialties
    public List<SpecialtyModel> findAll() {
        return specialtyRepository.findAll();
    }

    // delete by id
    @Transactional
    public void deleteById(UUID id) {
        try {
            specialtyRepository.deleteById(id);
        } catch (EmptyResultDataAccessException e) {
            throw new EntityNotFound("aaa");
        }
    }

    // delete all
    @Transactional
    public void deleteAll() {
        specialtyRepository.deleteAll();
    }

}

