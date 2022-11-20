package com.api.lores.dto;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Data
public class PersonDto {

    @NotBlank
    @Size(max = 11)
    private String cpfNumber;
}