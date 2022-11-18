package com.api.lores.treatments;

import com.api.lores.specialty.SpecialtyModel;
import lombok.Data;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Set;
import java.util.UUID;

@Entity
@Data
@Table(name = "treatments")
public class TreatmentModel {
    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")
    @Column(name = "treatment_id", updatable = false, nullable = false)
    @Type(type = "org.hibernate.type.UUIDCharType")
    private UUID id;

    @Column (nullable = false, length=20)
    private String fieldOfSpecialty;

    @Column(nullable = false, length = 20)
    private String procedureName;

    @Column(nullable = false)
    private BigDecimal priceAppointment;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name="specialty_id", referencedColumnName = "specialty_id")
    private SpecialtyModel specialty;

    public void assignSpecialty(SpecialtyModel specialty) {
        this.specialty = specialty;
    }
}
