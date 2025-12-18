package com.p0_arcade.service;

import java.util.List;
import java.util.Optional;

public interface ServiceInterface<T, U> {

    //CREATE
    public Integer createEntity(T entity);

    //READ
    public List<T> readAllEntities();
    public Optional<T> readEntityById(Integer id);

    //UPDATE
    public T updateEntity(T entity);

    //DELETE
    public void deleteEntityById(Integer id);

    // Conversation
    public Optional<U> convertEntityToModel(T entity);
    public Optional<U> getModelById(Integer id);
}
