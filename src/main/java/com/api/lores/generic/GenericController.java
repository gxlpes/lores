package com.api.lores.generic;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public abstract class GenericController<T> {

    public abstract GenericService<T> getService();

    @GetMapping
    public ResponseEntity<Iterable<T>> findAll() {

        return ResponseEntity.ok(getService().findAll());
    }

    @PostMapping
    public ResponseEntity<T> save(T entity) {

        return ResponseEntity.ok(getService().save(entity));
    }

    // @DeleteMapping, @PutMapping
    // These mappings will automatically be inherited by
    // the child class. So in the case of findAll(), the API
    // will have a GET mapping on /category as well as a GET
    // mapping on /product. So, by defining and annotating the
    // CRUD operations in the parent class, they will automatically
    // become available in all child classes.
}