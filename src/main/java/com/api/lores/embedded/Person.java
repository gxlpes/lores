package com.api.lores.embedded;

import lombok.Data;
import java.time.LocalDate;
import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.Embedded;

@Embeddable
@Data
public class Person {

    @Column(length = 60)
    private String fullName;

    @Column
    private LocalDate birthDate;

    @Column(length = 11)
    private String cpfNumber;

    @Column(nullable = true, length = 11)
    private String telephoneNumber;

    @Column(nullable = true, length = 30)
    private String emailAddress;

    @Embedded
    private Address address;

}