package com.vladis1350.shop.repositories.interfaces;

import java.sql.SQLException;
import java.util.List;

public interface MyRepositoryInterface<T> {
    void save(T obj) throws SQLException;
    List<T> findAll() throws SQLException;
    T getById(Long id) throws SQLException;
    void delete(Long id) throws SQLException;
    void update(T obj) throws SQLException;
}
