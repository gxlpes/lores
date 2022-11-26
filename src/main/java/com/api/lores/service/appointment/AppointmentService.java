package com.api.lores.service.appointment;

import com.api.lores.dto.AppointmentDto;
import com.api.lores.model.AppointmentModel;
import com.api.lores.exception.NotFoundException;
import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
public interface AppointmentService  {

    AppointmentDto findById(UUID id) throws NotFoundException;

    ResponseEntity<Object> findAll() throws NotFoundException;

    ResponseEntity<Object> save(AppointmentDto dto);

    ResponseEntity<String> update(UUID id, AppointmentDto dto) throws NotFoundException;

    ResponseEntity<String> deleteAll() throws NotFoundException;

    ResponseEntity<Object> deleteById(UUID id) throws NotFoundException;

    AppointmentModel findOrFail(UUID id);

    Optional<List<AppointmentModel>> findByPatientId(UUID id);

    Optional<List<AppointmentModel>> findByDentistId(UUID id);
}