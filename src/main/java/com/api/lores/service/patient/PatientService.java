package com.api.lores.service.patient;

import com.api.lores.dto.PatientDto;
import com.api.lores.model.DentistModel;
import com.api.lores.exception.NotFoundException;
import com.api.lores.model.PatientModel;
import org.springframework.http.ResponseEntity;

import java.util.UUID;
public interface PatientService {

    PatientDto findById(UUID id) throws NotFoundException;

    ResponseEntity<Object> findAll() throws NotFoundException;

    ResponseEntity<Object> save(PatientDto dto);

    ResponseEntity<String> update(UUID id, PatientDto dto) throws NotFoundException;

    ResponseEntity<String> deleteAll() throws NotFoundException;

    ResponseEntity<Object> deleteById(UUID id) throws NotFoundException;

    PatientModel findOrFail(UUID id);
}