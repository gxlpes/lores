package com.api.lores.generic;

import com.api.lores.generic.GenericRepository;

abstract class GenericService<T> {

    public abstract GenericRepository<T> getRepository();

    public Iterable<T> findAll() {

        return getRepository().findAll();
    }

    public T save(T entity) {

        return getRepository().save(entity);
    }

}