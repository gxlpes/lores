package com.api.lores.entities.treatments;

import com.api.lores.entities.specialty.SpecialtyModel;
import lombok.*;
import org.hibernate.Hibernate;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Objects;
import java.util.UUID;

@Entity
@Getter
@Setter
@ToString
@RequiredArgsConstructor
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

    @Column(nullable = false)
    private BigDecimal priceAppointment;

    @ManyToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "specialty_id", referencedColumnName = "specialty_id")
    private SpecialtyModel specialty;

    public void assignSpecialty(SpecialtyModel specialty) {
        this.specialty = specialty;
    }


    // lombok
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        TreatmentModel that = (TreatmentModel) o;
        return id != null && Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}
