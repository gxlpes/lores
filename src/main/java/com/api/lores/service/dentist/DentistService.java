package com.api.lores.service.dentist;

import com.api.lores.dto.DentistDto;
import com.api.lores.entity.DentistModel;
import com.api.lores.exception.NotFoundException;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.UUID;
@Service
public interface DentistService {

    DentistDto findById(UUID id) throws NotFoundException;

    ResponseEntity<Object> findAll() throws NotFoundException;

    ResponseEntity<Object> save(DentistDto dto);

    ResponseEntity<String> update(UUID id, DentistDto dto) throws NotFoundException;

    ResponseEntity<String> deleteAll() throws NotFoundException;

    ResponseEntity<Object> deleteById(UUID id) throws NotFoundException;

    DentistModel findOrFail(UUID idDent);
}