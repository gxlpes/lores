package com.api.lores.generic;

import com.api.lores.exception.NotFoundException;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public abstract class GenericService<T>  {

    public abstract GenericRepository<T> getRepository();

    public List<T> findAll() throws NotFoundException {
        return getRepository().findAll();
    }

    public Optional<T> findById(UUID id) throws NotFoundException {
        return getRepository().findById(id);
    }

    @Transactional
    public T save(T entity) {
        return getRepository().save(entity);
    }

    @Transactional
    public void delete(T entity) {
        getRepository().delete(entity);
    }

    @Transactional
    public void deleteAll() {
        getRepository().deleteAll();
    }

}