package com.api.lores.controller;

import com.api.lores.dto.AppointmentDto;
import com.api.lores.exception.NotFoundException;
import com.api.lores.model.AppointmentModel;
import com.api.lores.service.appointment.AppointmentService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@CrossOrigin(
        allowCredentials = "true",
        origins = {"http://localhost:3000", "http://localhost:8080"},
        allowedHeaders = "*")
@RestController
@RequestMapping("/appointments")
public class AppointmentController {

    final AppointmentService appointmentService;

    public AppointmentController(AppointmentService appointmentService) {
        this.appointmentService = appointmentService;
    }

    @PostMapping
    @PreAuthorize("hasAnyRole('ROLE_USER', 'ROLE_ADMIN')")
    public ResponseEntity<Object> saveAppointment(@RequestBody @Valid AppointmentDto appointmentDto) {
        return appointmentService.save(appointmentDto);
    }

    @GetMapping
    public ResponseEntity<Object> getAllAppointments() throws NotFoundException {
        return appointmentService.findAll();
    }

    @GetMapping("/{id}")
    public AppointmentDto getSingleAppointment(@PathVariable UUID id) throws NotFoundException {
        return appointmentService.findById(id);
    }

    @GetMapping("/patient/{id}")
    public Optional<List<AppointmentModel>> getAppointmentByPatientId(@PathVariable(value = "id") UUID id) {
        return appointmentService.findByPatientId(id);
    }

    @GetMapping("/dentist/{id}")
    public Optional<List<AppointmentModel>> getAppointByDentistId(@PathVariable(value = "id") UUID id) {
        return appointmentService.findByDentistId(id);
    }

    @DeleteMapping
    public ResponseEntity<String> deleteAllAppointments() throws NotFoundException {
        return appointmentService.deleteAll();
    }

    @DeleteMapping("/{id}")
    public void Appointment(@PathVariable UUID id) throws NotFoundException {
        appointmentService.deleteById(id);
    }

    @PutMapping("/{id}")
    public ResponseEntity<String> updateAppointment(@PathVariable UUID id, @RequestBody AppointmentDto appointmentDto) throws NotFoundException {
        return appointmentService.update(id, appointmentDto);
    }
}