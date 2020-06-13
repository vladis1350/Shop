package com.vladis1350.service;

import com.vladis1350.bean.Category;
import com.vladis1350.repositories.CategoryRepository;
import com.vladis1350.service.interfaces.ServiceInterface;
import org.springframework.stereotype.Service;

import java.sql.SQLException;
import java.util.List;

@Service
public class CategoryService implements ServiceInterface<Category, Long> {
    private final CategoryRepository categoryRepository;

    public CategoryService(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

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
