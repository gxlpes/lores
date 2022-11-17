package com.api.lores.appointment;

import com.api.lores.dentist.DentistModel;
import com.api.lores.patient.PatientModel;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Data
@Entity
@Table(name = "appointments")
public class AppointmentModel {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "id_appointment")
    private UUID id;

    @ManyToOne
    @JoinColumn(name = "pat_id", referencedColumnName = "patient_id")
    private PatientModel patient;

    @ManyToOne
    @JoinColumn(name = "dent_id", referencedColumnName = "dentist_id")
    private DentistModel dentist;

    @Column(nullable = false, length = 255)
    private String reason;

    @Column(nullable = false, length = 255)
    private String prescription;

    @Column(nullable = false)
    private BigDecimal priceAppointment;

    @Column
    private LocalDateTime dateAppointment;

    @Column
    private LocalDateTime dateAppointmentRegistration;

    @Column
    private LocalDateTime dateAppointmentUpdate;

}