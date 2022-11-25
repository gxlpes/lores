package com.api.lores.controller;

import com.api.lores.dto.AppointmentDto;
import com.api.lores.entity.AppointmentModel;
import com.api.lores.service.AppointmentService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@CrossOrigin
@RestController
@RequestMapping("/appointments")
public class AppointmentController {

    final AppointmentService appointmentService;

    public AppointmentController(AppointmentService appointmentService) {
        this.appointmentService = appointmentService;
    }

    @PostMapping
    public ResponseEntity<Object> saveAppointment(@RequestBody @Valid AppointmentDto appointmentDto) {
        return appointmentService.save(appointmentDto);
    }

    @GetMapping
    public ResponseEntity<Object> getAllAppointments() {
        return appointmentService.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Object> getSingleAppointment(@PathVariable UUID id) {
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
    public ResponseEntity<String> deleteAllAppointments() {
        return appointmentService.deleteAll();
    }

    @DeleteMapping("/{id}")
    public void Appointment(@PathVariable UUID id) {
        appointmentService.deleteById(id);
    }

    @PutMapping("/{id}")
    public ResponseEntity<AppointmentModel> updateAppointment(@PathVariable UUID id, @RequestBody AppointmentDto appointmentDto) {
        return appointmentService.update(id, appointmentDto);
    }
}