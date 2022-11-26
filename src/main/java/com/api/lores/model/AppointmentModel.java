package com.api.lores.model;

import lombok.Data;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.UUID;

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
    private LocalDate dateAppointment;

    @Column
    private LocalDateTime dateRegistration;

    @Column
    private LocalDateTime dateAppointmentLastUpdate;

}