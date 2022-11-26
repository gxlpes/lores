package com.api.lores.model;

import lombok.*;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.UUID;

@Data
@Entity
@Table(name = "treatments")
public class TreatmentModel {

    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")
    @Column(name = "treatment_id", updatable = false, nullable = false)
    @Type(type = "org.hibernate.type.UUIDCharType")
    private UUID id;

    @Column(nullable = false, length = 20)
    private String fieldOfSpecialty;

    @Column(nullable = false, length = 20)
    private String procedureName;

    @Column(nullable = true)
    private BigDecimal priceAppointment;

    @ManyToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "specialty_id", referencedColumnName = "specialty_id")
    private SpecialtyModel specialty;

    public void assignSpecialty(SpecialtyModel specialty) {
        this.specialty = specialty;
    }

}
