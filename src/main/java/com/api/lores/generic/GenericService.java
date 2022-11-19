package com.api.lores.generic;

public abstract class GenericService<T> {

    public abstract GenericRepository<T> getRepository();

    public Iterable<T> findAll() {

        return getRepository().findAll();
    }

    public T save(T entity) {

        return getRepository().save(entity);
    }

}