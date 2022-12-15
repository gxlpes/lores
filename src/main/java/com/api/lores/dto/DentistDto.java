package com.api.lores.dto;

import com.api.lores.model.embedded.Person;
import lombok.Data;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Data
public class DentistDto {

    @Size(max = 11)
    private String croNumber;

    @Valid
    Person person;

}