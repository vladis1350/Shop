package com.vladis1350.shop.repositories;

import com.vladis1350.shop.bean.Category;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;

@RunWith(MockitoJUnitRunner.class)
class CategoryRepositoryTest {

    @Mock
    private CategoryRepository categoryRepository;

    private Category category;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
        category = Category.builder()
                .id(9L)
                .nameCategory("Category test")
                .build();
    }

    @Test
    void findAll() throws SQLException {
        ArrayList<Category> categoryList = new ArrayList<>();
        categoryList.add(category);
        given(this.categoryRepository.findAll()).willReturn(categoryList);
        List<Category> categoryList1 = this.categoryRepository.findAll();
        assertThat(categoryList.size() == categoryList1.size());
    }

    @Test
    void getById() throws SQLException {
        given(this.categoryRepository.getById(any()))
                .willReturn(category);
        Category categoryTest = this.categoryRepository.getById(9L);
        assertThat(categoryTest.getId()).isEqualTo(9);
    }
}