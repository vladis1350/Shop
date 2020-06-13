package com.vladis1350.service.interfaces;

import com.vladis1350.bean.Product;

import java.sql.SQLException;
import java.util.List;

public interface ServiceInterface<T, Long> {
    void save(T entity) throws SQLException;
    void update(T entity) throws SQLException;
    T getById(Long id) throws SQLException;
    void remove(Long id) throws SQLException;
    List<T> findAll() throws SQLException;
}
