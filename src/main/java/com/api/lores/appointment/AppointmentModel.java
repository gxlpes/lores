package com.api.lores.appointment;

import com.api.lores.dentist.DentistModel;
import com.api.lores.patient.PatientModel;
import com.api.lores.specialty.SpecialtyModel;
import com.api.lores.treatments.TreatmentModel;
import lombok.Data;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Type;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;
import javax.persistence.*;

@Data
@Entity
@Table(name = "appointments")
public class AppointmentModel {

    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")
    @Column(name = "appointment_id", updatable = false, nullable = false)
    @Type(type = "org.hibernate.type.UUIDCharType")
    private UUID id;

    @OneToMany(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "appointment_id")
    private List<TreatmentModel> treatments;

    @ManyToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "pat_id", referencedColumnName = "patient_id")
    private PatientModel patient;

    @ManyToOne(cascade = CascadeType.PERSIST)
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

}