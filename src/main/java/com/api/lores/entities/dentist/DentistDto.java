package com.api.lores.entities.dentist;

import lombok.Data;

import javax.validation.constraints.NotBlank;

@Data
public class DentistDto {

    @NotBlank
    private String croNumber;

}
