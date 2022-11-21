package com.api.lores.dto;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.math.BigDecimal;

@Data
public class TreatmentDto {

    @NotBlank
    private String fieldOfSpecialty;

    @Size(max = 25)
    @NotBlank
    private String procedureName;

    private BigDecimal priceAppointment;
}