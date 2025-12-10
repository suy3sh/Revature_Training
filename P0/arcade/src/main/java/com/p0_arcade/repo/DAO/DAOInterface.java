package com.p0_arcade.repo.DAO;

import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

public interface DAOInterface<T> {
    // CRUD

    // CREATE
    public Integer insert(T entity) throws SQLException;

    // READ BY ID
    public Optional<T> findById(Integer id) throws SQLException;

    // READ ALL
    public List<T> findAll() throws SQLException;

    // UPDATE
    public void update(T entity) throws SQLException;

    // DELETE
    public void deleteById(Integer id) throws SQLException;
}
