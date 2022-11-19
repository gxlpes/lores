package com.api.lores.entities.appointment;

import com.api.lores.entities.dentist.DentistModel;
import com.api.lores.entities.patient.PatientModel;
import com.api.lores.entities.treatments.TreatmentModel;
import lombok.*;
import org.hibernate.Hibernate;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Objects;
import java.util.UUID;

@Getter
@Setter
@ToString
@RequiredArgsConstructor
@Entity
@Table(name = "appointments")
public class AppointmentModel {

    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")
    @Column(name = "appointment_id", updatable = false, nullable = false)
    @Type(type = "org.hibernate.type.UUIDCharType")
    private UUID id;

    @ManyToOne
    @JoinColumn(name = "treat_id", referencedColumnName = "treatment_id")
    private TreatmentModel treatment;

    @ManyToOne
    @JoinColumn(name = "pat_id", referencedColumnName = "patient_id")
    private PatientModel patient;

    @ManyToOne
    @JoinColumn(name = "dent_id", referencedColumnName = "dentist_id")
    private DentistModel dentist;

    @Column(nullable = false, length = 255)
    private String reason;

    @Column
    private LocalDateTime dateAppointment;

    @Column
    private LocalDateTime dateAppointmentRegistration;

    @Column
    private LocalDateTime dateAppointmentUpdate;


    // lombok
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        AppointmentModel that = (AppointmentModel) o;
        return id != null && Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}