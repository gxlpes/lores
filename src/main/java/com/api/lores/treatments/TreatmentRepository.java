package com.api.lores.treatments;

import com.api.lores.specialty.SpecialtyModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface TreatmentRepository extends JpaRepository<TreatmentModel, UUID> {
}
