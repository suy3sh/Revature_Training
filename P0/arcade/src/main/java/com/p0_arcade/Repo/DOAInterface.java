package com.p0_arcade.repo;

import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

public interface DOAInterface<T> {
    // CRUD

    // CREATE
    public T insert(T entity) throws SQLException;

    // READ BY ID
    public T findById(int id) throws SQLException;

    // READ ALL
    public List<T> findAll() throws SQLException;

    // UPDATE
    public void update(T entity) throws SQLException;

    // DELETE
    public void deleteById(int id) throws SQLException;
}
