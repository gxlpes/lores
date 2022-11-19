package com.api.lores.entities.appointment;

import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class AppointmentService {

    final AppointmentRepository appointmentRepository;

    public AppointmentService(AppointmentRepository appointmentRepository) {
        this.appointmentRepository = appointmentRepository;
    }

    // save method
    @Transactional
    public AppointmentModel save(AppointmentModel appointmentModel) {
        return appointmentRepository.save(appointmentModel);
    }

    // find by id
    public Optional<AppointmentModel> findById(UUID id) {
        return appointmentRepository.findById(id);
    }

    // find by patient id
    public Optional<List<AppointmentModel>> findByPatientId(UUID id) {
        return appointmentRepository.findByPatientId(id);
    }

    // find by dentist id
    public Optional<List<AppointmentModel>> findByDentistId(UUID id) {
        return appointmentRepository.findByDentistId(id);
    }

    // find all dentists
    public List<AppointmentModel> findAll() {
        return appointmentRepository.findAll();
    }

    // delete by id
    @Transactional
    public void delete(AppointmentModel appointmentModel) {
        appointmentRepository.delete(appointmentModel);
    }

    // delete all
    @Transactional
    public void deleteAll() {
        appointmentRepository.deleteAll();
    }

}
