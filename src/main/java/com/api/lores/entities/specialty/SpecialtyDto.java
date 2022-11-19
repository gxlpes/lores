package com.api.lores.entities.specialty;

import lombok.Data;

import javax.validation.constraints.NotBlank;

@Data
public class SpecialtyDto {

    @NotBlank
    private String title;

}
