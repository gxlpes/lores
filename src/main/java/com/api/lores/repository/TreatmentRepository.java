package com.api.lores.repository;

import com.api.lores.model.TreatmentModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface TreatmentRepository extends JpaRepository<TreatmentModel, UUID> {
}