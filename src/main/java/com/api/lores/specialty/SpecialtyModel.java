package com.api.lores.specialty;

import com.api.lores.treatments.TreatmentModel;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

@Table(name = "specialties")
@Entity
@Data
public class SpecialtyModel {

    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")
    @Column(name = "specialty_id", updatable = false, nullable = false)
    @Type(type = "org.hibernate.type.UUIDCharType")
    private UUID id;

    @Column(nullable = false, length = 20)
    private String title;

    @JsonIgnore
    @OneToMany(mappedBy = "specialty")
    private Set<TreatmentModel> treatments = new HashSet<>();

}