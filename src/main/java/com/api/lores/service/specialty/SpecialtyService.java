package com.api.lores.service.specialty;

import com.api.lores.dto.SpecialtyDto;
import com.api.lores.model.DentistModel;
import com.api.lores.exception.NotFoundException;
import com.api.lores.model.SpecialtyModel;
import org.springframework.http.ResponseEntity;

import java.util.UUID;
public interface SpecialtyService  {

    SpecialtyDto findById(UUID id) throws NotFoundException;

    ResponseEntity<Object> findAll() throws NotFoundException;

    ResponseEntity<Object> save(SpecialtyDto dto);

    ResponseEntity<String> update(UUID id, SpecialtyDto dto) throws NotFoundException;

    ResponseEntity<String> deleteAll() throws NotFoundException;

    ResponseEntity<Object> deleteById(UUID id) throws NotFoundException;

    SpecialtyModel findOrFail(UUID id);

    ResponseEntity<Object> assignDentistToSpecialty(UUID specialtyId, UUID dentistId);
}