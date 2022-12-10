package com.api.lores.repository;

import com.api.lores.model.PatientModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface PatientRepository extends JpaRepository<PatientModel, UUID> {
    boolean existsByPersonCpfNumber(String person_cpfNumber);

    Optional<PatientModel> findByPersonCpfNumber(String person_cpfNumber);
}