package com.api.lores.service.appointment;

import com.api.lores.dto.AppointmentDto;
import com.api.lores.exception.EntityNotFound;
import com.api.lores.exception.NotFoundException;
import com.api.lores.mapper.AppointmentMapper;
import com.api.lores.model.AppointmentModel;
import com.api.lores.repository.AppointmentRepository;
import org.hibernate.tool.schema.SchemaToolingLogging;
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
public class AppointmentServiceImpl implements AppointmentService {

    public static final String APPOINTMENT = "Appointment";
    private final AppointmentRepository appointmentRepository;
    private final AppointmentMapper appointmentMapper = AppointmentMapper.INSTANCE;

    public AppointmentServiceImpl(AppointmentRepository appointmentRepository) {
        this.appointmentRepository = appointmentRepository;
    }

    @Override
    @Transactional
    public ResponseEntity<Object> save(AppointmentDto dto) {
        var appointmentToSave = appointmentMapper.toModel(dto);
        appointmentToSave.setDateRegistration(LocalDateTime.now());
        appointmentRepository.save(appointmentToSave);
        LOGGER.info("Saved a " + APPOINTMENT);
        return ResponseEntity.status(HttpStatus.CREATED).body(appointmentToSave);
    }

    public AppointmentModel findOrFail(UUID id) {
        return appointmentRepository.findById(id).orElseThrow(() -> {
            LOGGER.error("Error trying to find" + APPOINTMENT);
            throw new EntityNotFound(String.format(APPOINTMENT + "does not exist with the %s", id));
        });
    }

    @Override
    public AppointmentDto findById(UUID id) throws NotFoundException {
        var appointment = appointmentRepository.findById(id).orElseThrow(() -> new NotFoundException());
        return appointmentMapper.toDto(appointment);
    }

    @Override
    public ResponseEntity<Object> findAll() throws NotFoundException {
        var appointmentList = appointmentRepository.findAll();
        if (appointmentList.isEmpty()) {
            return ResponseEntity.status(HttpStatus.OK).body("No appointments were found");
        } else {
            return ResponseEntity.status(HttpStatus.OK).body(appointmentList);
        }
    }

    @Override
    public ResponseEntity<String> update(UUID id, AppointmentDto dto) throws NotFoundException {
        appointmentRepository.findById(id).orElseThrow(() -> new NotFoundException());
        var appointmentToSave = appointmentMapper.toModel(dto);
        appointmentToSave.setId(id);
        appointmentToSave.setDateAppointmentLastUpdate(LocalDateTime.now());
        appointmentRepository.save(appointmentToSave);
        LOGGER.info("Updated a " + APPOINTMENT);
        return ResponseEntity.status(HttpStatus.OK).body("A " + APPOINTMENT + " was updated");
    }

    @Override
    public ResponseEntity<String> deleteAll() throws NotFoundException {
        appointmentRepository.deleteAll();
        SchemaToolingLogging.LOGGER.info("All " + APPOINTMENT + "s were deleted.");
        return ResponseEntity.status(HttpStatus.OK).body("All " + APPOINTMENT + "s were deleted");
    }

    @Override
    public ResponseEntity<Object> deleteById(UUID id) throws NotFoundException {
        var dentist = appointmentRepository.findById(id).orElseThrow(() -> new NotFoundException());
        appointmentRepository.delete(dentist);
        SchemaToolingLogging.LOGGER.info("A " + APPOINTMENT + " was deleted with the ID " + id + " .");
        return ResponseEntity.status(HttpStatus.OK).body("A " + APPOINTMENT + " was deleted");
    }

    public Optional<List<AppointmentModel>> findByPatientId(UUID id) {
        return appointmentRepository.findByPatientId(id);
    }

    public Optional<List<AppointmentModel>> findByDentistId(UUID id) {
        return appointmentRepository.findByDentistId(id);
    }

}