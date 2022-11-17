package com.api.lores.dentist;

import com.api.lores.embedded.Person;
import com.api.lores.specialty.SpecialtyModel;
import lombok.Data;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.util.List;
import java.util.UUID;

@Data
@Entity
@Table(name="dentists")
public class DentistModel {

    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")
    @Column(name = "dentist_id", updatable = false, nullable = false)
    @Type(type = "org.hibernate.type.UUIDCharType")
    private UUID id;

    @Column(nullable = false, unique = true, length = 10)
    private String croNumber;

    @Embedded
    private Person person;

    @OneToMany(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "dentist_id")
    private List<SpecialtyModel> specialties;
}
