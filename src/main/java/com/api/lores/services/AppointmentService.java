package com.api.lores.services;

import com.api.lores.entity.AppointmentModel;
import com.api.lores.exception.EntityNotFound;
import com.api.lores.repository.AppointmentRepository;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
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
    public AppointmentModel save(AppointmentModel appointmentModel) {
        LOGGER.info(APPOINTMENT + " was saved");
        return appointmentRepository.save(appointmentModel);
    }

    public AppointmentModel findOrFail(UUID id) {
        return appointmentRepository.findById(id).orElseThrow(() -> {
            LOGGER.error("Error trying to find" + APPOINTMENT);
            throw new EntityNotFound(String.format("Entity does not exist with the %s", id));
        });
    }

    public Optional<List<AppointmentModel>> findByPatientId(UUID id) {
        return appointmentRepository.findByPatientId(id);
    }

    public Optional<List<AppointmentModel>> findByDentistId(UUID id) {
        return appointmentRepository.findByDentistId(id);
    }

    public List<AppointmentModel> findAll() {
        return appointmentRepository.findAll();
    }

    @Transactional
    public void deleteById(UUID id) {
        try {
            LOGGER.info(APPOINTMENT + " was created");
            appointmentRepository.deleteById(id);
        } catch (EmptyResultDataAccessException e) {
            LOGGER.error("Error trying to delete an " + APPOINTMENT);
            throw new EntityNotFound(APPOINTMENT + " do not exist");
        }
    }

    @Transactional
    public void deleteAll() {
        LOGGER.info("Deleting all " + APPOINTMENT);
        appointmentRepository.deleteAll();
    }

}