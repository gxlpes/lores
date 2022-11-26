package com.api.lores.service.treatment;

import com.api.lores.dto.TreatmentDto;
import com.api.lores.exception.EntityNotFound;
import com.api.lores.exception.NotFoundException;
import com.api.lores.mapper.TreatmentMapper;
import com.api.lores.model.SpecialtyModel;
import com.api.lores.model.TreatmentModel;
import com.api.lores.repository.TreatmentRepository;
import com.api.lores.service.specialty.SpecialtyService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

import javax.transaction.Transactional;
import java.util.UUID;

import static com.api.lores.log.Logging.LOGGER;

@Service
public class TreatmentServiceImpl implements TreatmentService {

    public static final String TREATMENT = "treatment";
    private final SpecialtyService specialtyService;
    private final TreatmentRepository treatmentRepository;
    private final TreatmentMapper treatmentMapper = TreatmentMapper.INSTANCE;

    public TreatmentServiceImpl(SpecialtyService specialtyService, TreatmentRepository treatmentRepository) {
        this.specialtyService = specialtyService;
        this.treatmentRepository = treatmentRepository;
    }

    @Override
    @Transactional
    public ResponseEntity<Object> save(TreatmentDto dto) {
        var treatmentToSave = treatmentMapper.toModel(dto);
        treatmentRepository.save(treatmentToSave);
        LOGGER.info("Saved a " + TREATMENT);
        return ResponseEntity.status(HttpStatus.OK).body(treatmentToSave);
    }

    @Override
    public TreatmentDto findById(UUID id) throws NotFoundException {
        var treatment = treatmentRepository.findById(id).orElseThrow(() -> new NotFoundException());
        return treatmentMapper.toDto(treatment);
    }

    @Override
    public ResponseEntity<Object> findAll() throws NotFoundException {
        var treatmentList = treatmentRepository.findAll();
        if (treatmentList.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No treatments were found");
        } else {
            return ResponseEntity.status(HttpStatus.OK).body(treatmentList);
        }
    }

    @Override
    public ResponseEntity<String> update(UUID id, TreatmentDto dto) throws NotFoundException {
        treatmentRepository.findById(id).orElseThrow(() -> new NotFoundException());
        var treatmentToSave = treatmentMapper.toModel(dto);
        treatmentToSave.setId(id);
        treatmentRepository.save(treatmentToSave);
        LOGGER.info("Updated a " + TREATMENT);
        return ResponseEntity.status(HttpStatus.OK).body("A " + TREATMENT + " was updated");
    }

    @Override
    public ResponseEntity<String> deleteAll() throws NotFoundException {
        treatmentRepository.deleteAll();
        LOGGER.info("All " + TREATMENT + "s were deleted.");
        return ResponseEntity.status(HttpStatus.OK).body("All " + TREATMENT + "s were deleted");
    }

    @Override
    public ResponseEntity<Object> deleteById(UUID id) throws NotFoundException {
        var treatment = treatmentRepository.findById(id).orElseThrow(() -> new NotFoundException());
        treatmentRepository.delete(treatment);
        LOGGER.info("A " + TREATMENT + " was deleted with the ID " + id + " .");
        return ResponseEntity.status(HttpStatus.OK).body("A " + TREATMENT + " was deleted");
    }

    @Override
    public TreatmentModel findOrFail(UUID id) {
        return treatmentRepository.findById(id).orElseThrow(() -> {
            LOGGER.error("Error trying to find " + TREATMENT);
            throw new EntityNotFound(String.format("Entity does not exist with the %s", id));
        });
    }

    public ResponseEntity<Object> assignSpecialtyToTreatment(@PathVariable UUID treatmentId, @PathVariable UUID specialtyId)  {
        TreatmentModel treatment = findOrFail(treatmentId);
        SpecialtyModel specialty = specialtyService.findOrFail(specialtyId);
        treatment.assignSpecialty(specialty);
        treatmentRepository.save(treatment);
        return ResponseEntity.status(HttpStatus.OK).body("Specialty assigned to treatment.");

    }

}