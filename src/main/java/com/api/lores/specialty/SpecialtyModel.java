package com.api.lores.specialty;

import com.api.lores.dentist.DentistModel;
import com.api.lores.treatments.TreatmentModel;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import lombok.*;
import org.hibernate.Hibernate;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;
import java.util.UUID;

@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
@Table(name = "specialties")
@Entity
@Getter
@Setter
@ToString
@RequiredArgsConstructor

public class SpecialtyModel {
    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")
    @Column(name = "specialty_id", updatable = false, nullable = false)
    @Type(type = "org.hibernate.type.UUIDCharType")
    private UUID id;

    @Column(nullable = false, length = 20)
    private String title;

    @JsonBackReference
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "dentist_id", referencedColumnName = "dentist_id")
    private DentistModel dentist;

    @JsonIgnore
    @OneToMany(mappedBy = "specialty", orphanRemoval = true, cascade = CascadeType.PERSIST)
    @ToString.Exclude
    private Set<TreatmentModel> treatments = new HashSet<>();

    public void assignDentist(DentistModel dentist) {
        this.dentist = dentist;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        SpecialtyModel that = (SpecialtyModel) o;
        return id != null && Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}