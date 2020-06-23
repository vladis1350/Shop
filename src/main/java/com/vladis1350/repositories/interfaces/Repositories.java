package com.vladis1350.repositories.interfaces;

import java.sql.SQLException;
import java.util.List;

public interface Repositories<T, Long> {
    void save(T obj) throws SQLException;
    List<T> findAll() throws SQLException;
    T getById(Long id) throws SQLException;
    void delete(Long id) throws SQLException;
    void update(T obj) throws SQLException;
}
