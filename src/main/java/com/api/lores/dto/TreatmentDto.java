package com.api.lores.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class TreatmentDto {

    @NotBlank
    private String fieldOfSpecialty;

    @Size(max = 5)
    @NotBlank
    private String priceAppointment;

    @Size(max = 25)
    @NotBlank
    private String procedureName;

}