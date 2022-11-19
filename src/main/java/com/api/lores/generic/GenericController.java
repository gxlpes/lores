package com.api.lores.generic;

import com.api.lores.exception.NotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
public abstract class GenericController<T> {

    public abstract GenericService<T> getService();

    @PostMapping
    public ResponseEntity<T> save(@RequestBody T entityRequest) {
        return ResponseEntity.status(HttpStatus.CREATED).body(getService().save(entityRequest));
    }

    @GetMapping
    public ResponseEntity<List<T>> findAll() throws NotFoundException {
        return ResponseEntity.status(HttpStatus.OK).body(getService().findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Object> getSingle(@PathVariable UUID id) throws NotFoundException {
        Optional<T> entityModelOptional = getService().findById(id);
        if (entityModelOptional.isEmpty()) throw new NotFoundException();
        return ResponseEntity.status(HttpStatus.OK).body(entityModelOptional.get());
    }

    @DeleteMapping
    public ResponseEntity<Object> deleteAll() {
        getService().deleteAll();
        return ResponseEntity.status(HttpStatus.OK).body("All entities were deleted successfully.");
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> deleteSingle(@PathVariable(value = "id") UUID id) throws NotFoundException {
        Optional<T> entityModelOptional = getService().findById(id);
        if (entityModelOptional.isEmpty()) {
            throw new NotFoundException();
        } else {
            getService().delete(entityModelOptional.get());
            return ResponseEntity.status(HttpStatus.OK).body("Entity deleted successfully.");
        }
    }
}