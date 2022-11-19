package com.api.lores.entities.dentist;

import com.api.lores.embedded.Person;
import lombok.Data;

import javax.persistence.Embedded;
import javax.validation.constraints.NotBlank;

@Data
public class DentistDto {

    @NotBlank
    private String croNumber;

}
