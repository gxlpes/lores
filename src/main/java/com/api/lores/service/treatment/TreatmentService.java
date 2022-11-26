package com.api.lores.service.treatment;

import com.api.lores.dto.TreatmentDto;
import com.api.lores.exception.NotFoundException;
import com.api.lores.model.TreatmentModel;
import org.springframework.http.ResponseEntity;

import java.util.UUID;

public interface TreatmentService {

    TreatmentDto findById(UUID id) throws NotFoundException;

    ResponseEntity<Object> findAll() throws NotFoundException;

    ResponseEntity<Object> save(TreatmentDto dto);

    ResponseEntity<String> update(UUID id, TreatmentDto dto) throws NotFoundException;

    ResponseEntity<String> deleteAll() throws NotFoundException;

    ResponseEntity<Object> deleteById(UUID id) throws NotFoundException;

    TreatmentModel findOrFail(UUID id);

    ResponseEntity<Object> assignSpecialtyToTreatment(UUID treatmentId, UUID specialtyId);

}