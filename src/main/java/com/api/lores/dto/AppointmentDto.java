package com.api.lores.dto;

import com.api.lores.entity.embedded.Person;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import java.time.LocalDateTime;

@Data
public class AppointmentDto {

    @NotBlank
    private String reason;

    @NotBlank
    private LocalDateTime dateAppointment;
}