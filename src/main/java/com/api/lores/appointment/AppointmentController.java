package com.api.lores.appointment;

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

    @GetMapping("/{id}")
    public ResponseEntity<Object> getSingleAppointment(@PathVariable(value = "id") UUID id) {
        Optional<AppointmentModel> appointmentModelOptional = appointmentService.findById(id);
        if (appointmentModelOptional.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Appointment not found.");
        } else {
            return ResponseEntity.status(HttpStatus.OK).body(appointmentModelOptional.get());
        }
    }

    @GetMapping
    public ResponseEntity<List<AppointmentModel>> getAllAppointments() {
        return ResponseEntity.status(HttpStatus.OK).body(appointmentService.findAll());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> deleteAppointment(@PathVariable(value = "id") UUID id) {
        Optional<AppointmentModel> appointmentModelOptional = appointmentService.findById(id);
        if (appointmentModelOptional.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Appointment not found.");
        } else {
            appointmentService.delete(appointmentModelOptional.get());
            return ResponseEntity.status(HttpStatus.OK).body("Appointment deleted successfully.");
        }
    }

    @DeleteMapping
    public ResponseEntity<Object> deleteAllAppointments() {
        return ResponseEntity.status(HttpStatus.OK).body("All appointments were deleted successfully.");
    }

    @PutMapping("/{id}")
    public ResponseEntity<Object> updateAppointment(@PathVariable(value = "id") UUID id, @RequestBody AppointmentModel patient) {
        Optional<AppointmentModel> appointmentModelOptional = appointmentService.findById(id);
        if (appointmentModelOptional.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Appointment not found");
        } else {
            var appointmentModel = new AppointmentModel();
            BeanUtils.copyProperties(patient, appointmentModel);
            return ResponseEntity.status(HttpStatus.OK).body(appointmentService.save(appointmentModel));
        }

    }
}