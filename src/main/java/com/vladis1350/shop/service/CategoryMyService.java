package com.vladis1350.shop.service;

import com.vladis1350.shop.bean.Category;
import com.vladis1350.shop.repositories.CategoryRepository;
import com.vladis1350.shop.service.interfaces.MyServiceInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.SQLException;
import java.util.List;

@Service
public class CategoryMyService implements MyServiceInterface<Category> {

    @Autowired
    private CategoryRepository categoryRepository;

    @Override
    public void save(Category category) throws SQLException {
        this.categoryRepository.save(category);
    }

    @Override
    public void update(Category category) throws SQLException {
        this.categoryRepository.update(category);
    }

    @Override
    public Category getById(Long id) throws SQLException {
        return this.categoryRepository.getById(id);
    }

    @Override
    public void remove(Long id) throws SQLException {
        this.categoryRepository.delete(id);
    }

    @Override
    public List<Category> findAll() throws SQLException {
        return this.categoryRepository.findAll();
    }
}
