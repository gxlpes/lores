package com.api.lores.controller;

import com.api.lores.entity.AppointmentModel;
import com.api.lores.services.AppointmentService;
import org.springframework.beans.BeanUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
@CrossOrigin
@RequestMapping("/appointments")
public class AppointmentController {

    final AppointmentService appointmentService;

    public AppointmentController(AppointmentService appointmentService) {
        this.appointmentService = appointmentService;
    }

    @PostMapping
    public ResponseEntity<Object> saveAppointment(@RequestBody AppointmentModel appointmentRequest) {
        var appointmentModel = new AppointmentModel();
        BeanUtils.copyProperties(appointmentRequest, appointmentModel);
        return ResponseEntity.status(HttpStatus.CREATED).body(appointmentService.save(appointmentModel));
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<AppointmentModel> getAllSpecialties() {
        return appointmentService.findAll();
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public AppointmentModel getSingleAppointment(@PathVariable UUID id) {
        return appointmentService.findOrFail(id);
    }

    @GetMapping("/patient/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Optional<List<AppointmentModel>> getAppointmentByPatientId(@PathVariable(value = "id") UUID id) {
        return appointmentService.findByPatientId(id);
    }

    @DeleteMapping
    @ResponseStatus(HttpStatus.OK)
    public String deleteAllSpecialties() {
        appointmentService.deleteAll();
        return "All appointments were deleted successfully.";
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void Appointment(@PathVariable UUID id) {
        appointmentService.deleteById(id);
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.CREATED)
    public AppointmentModel updateAppointment(@PathVariable UUID id, @RequestBody AppointmentModel appointment) {
        AppointmentModel actualAppointment = appointmentService.findOrFail(id);
        BeanUtils.copyProperties(appointment, actualAppointment, "id");
        return appointmentService.save(appointment);
    }
}