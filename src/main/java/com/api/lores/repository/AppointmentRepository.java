package com.api.lores.repository;

import com.api.lores.model.AppointmentModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface AppointmentRepository extends JpaRepository<AppointmentModel, UUID> {

    Optional<List<AppointmentModel>> findByPatientId(UUID id);
    Optional<List<AppointmentModel>> findByDentistId(UUID id);

}