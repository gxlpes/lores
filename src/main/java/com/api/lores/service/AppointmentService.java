package com.api.lores.service;

import com.api.lores.dto.AppointmentDto;
import com.api.lores.entity.AppointmentModel;
import com.api.lores.exception.EntityNotFound;
import com.api.lores.repository.AppointmentRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static com.api.lores.log.Logging.LOGGER;

@Service
public class AppointmentService {

    public static final String APPOINTMENT = "Appointment";
    final AppointmentRepository appointmentRepository;

    public AppointmentService(AppointmentRepository appointmentRepository) {
        this.appointmentRepository = appointmentRepository;
    }

    @Transactional
    public ResponseEntity<Object> save(AppointmentDto appointmentDto) {
        var appointmentModel = convertDtoToModel(appointmentDto);
        LOGGER.info(APPOINTMENT + " was saved");
        return ResponseEntity.status(HttpStatus.CREATED).body(appointmentRepository.save(appointmentModel));
    }

    public AppointmentModel findOrFail(UUID id) {
        return appointmentRepository.findById(id).orElseThrow(() -> {
            LOGGER.error("Error trying to find" + APPOINTMENT);
            throw new EntityNotFound(String.format(APPOINTMENT + "does not exist with the %s", id));
        });
    }

    public Optional<List<AppointmentModel>> findByPatientId(UUID id) {
        return appointmentRepository.findByPatientId(id);
    }

    public Optional<List<AppointmentModel>> findByDentistId(UUID id) {
        return appointmentRepository.findByDentistId(id);
    }

    public ResponseEntity<Object> findById(UUID id) {
        var patientFound = findOrFail(id);
        return ResponseEntity.status(HttpStatus.OK).body(patientFound);
    }

    public ResponseEntity<Object> findAll() {
        var appointmentModelList = appointmentRepository.findAll();
        if (appointmentModelList.size() == 0) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No appointments were submitted");
        } else {
            return ResponseEntity.status(HttpStatus.OK).body(appointmentModelList);
        }
    }

    @Transactional
    public ResponseEntity<Object> deleteById(UUID id) {
        try {
            LOGGER.info(APPOINTMENT + " was deleted");
            appointmentRepository.deleteById(id);
            return ResponseEntity.status(HttpStatus.OK).body("Appointment was deleted");
        } catch (EmptyResultDataAccessException e) {
            LOGGER.error("Error trying to delete a " + APPOINTMENT);
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No appointments were submitted");
        }
    }

    @Transactional
    public ResponseEntity<String> deleteAll() {
        appointmentRepository.deleteAll();
        LOGGER.info("Deleting all " + APPOINTMENT);
        return ResponseEntity.status(HttpStatus.OK).body("All appointments were deleted");
    }

    public ResponseEntity<AppointmentModel> update (UUID id, AppointmentDto appointmentDto) {
        AppointmentModel appointmentModelToEdit = findOrFail(id);
        AppointmentModel appointmentModel = convertDtoToModel(appointmentDto);
        BeanUtils.copyProperties(appointmentModel, appointmentModelToEdit);
        appointmentModelToEdit.setDateAppointmentLastUpdate(LocalDateTime.now());
        return ResponseEntity.status(HttpStatus.OK).body(appointmentRepository.save(appointmentModelToEdit));
    }

    public AppointmentModel convertDtoToModel(AppointmentDto appointmentDto) {
        var appointmentModel = new AppointmentModel();
        BeanUtils.copyProperties(appointmentDto, appointmentModel);
        return appointmentModel;
    }
}