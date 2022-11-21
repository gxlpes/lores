package com.api.lores.dto;

import com.api.lores.entity.DentistModel;
import com.api.lores.entity.PatientModel;
import com.api.lores.entity.TreatmentModel;
import lombok.Data;

import javax.validation.Valid;
import javax.validation.constraints.FutureOrPresent;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.time.LocalDate;

@Data
public class AppointmentDto {

    @NotBlank
    @Size(max = 200)
    private String reason;

    @FutureOrPresent
    private LocalDate dateAppointment;

    @Valid
    private TreatmentModel treatment;

    @Valid
    private DentistModel dentist;

    @Valid
    private PatientModel patient;
}