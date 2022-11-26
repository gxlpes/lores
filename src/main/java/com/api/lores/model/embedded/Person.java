package com.api.lores.model.embedded;

import lombok.Data;
import java.time.LocalDate;
import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.Embedded;

@Embeddable
@Data
public class Person {

    @Column
    private String fullName;

    @Column
    private LocalDate birthdate;

    @Column
    private String cpfNumber;

    @Column(nullable = true)
    private String telephoneNumber;

    @Column(nullable = true)
    private String emailAddress;

    @Embedded
    private Address address;

}