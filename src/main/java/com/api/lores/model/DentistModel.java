package com.api.lores.model;

import com.api.lores.model.embedded.Person;
import com.fasterxml.jackson.annotation.*;
import lombok.*;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

@Getter
@Setter
@ToString
@RequiredArgsConstructor
@Entity
@Table(name = "dentists")
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
public class DentistModel {

    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")
    @Type(type = "org.hibernate.type.UUIDCharType")
    @Column(name = "dentist_id", updatable = false, nullable = false)
    private UUID id;

    @Column(nullable = false, unique = true, length = 10)
    private String croNumber;

    @Embedded
    private Person person;

    @OneToMany(mappedBy = "dentist", orphanRemoval = true, fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JsonManagedReference
    private Set<SpecialtyModel> specialties = new HashSet<>();

}