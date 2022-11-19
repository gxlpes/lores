package com.api.lores.entities.dentist;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface DentistRepository extends JpaRepository<DentistModel, UUID> {
    boolean existsByCroNumber(String croNumber);
}
