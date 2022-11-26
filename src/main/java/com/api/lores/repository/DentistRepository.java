package com.api.lores.repository;

import com.api.lores.model.DentistModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface DentistRepository extends JpaRepository<DentistModel, UUID> {

    Optional<Object> findByCroNumber(String croNumber);
}