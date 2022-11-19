package com.api.lores.embedded;

import lombok.Data;

import javax.persistence.Embedded;
import javax.validation.constraints.NotBlank;

@Data
public class PersonDto {

    @NotBlank
    private String fullName;

}
