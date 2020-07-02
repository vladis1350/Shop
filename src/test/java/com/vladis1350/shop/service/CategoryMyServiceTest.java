package com.vladis1350.shop.service;

import com.vladis1350.shop.bean.Category;
import com.vladis1350.shop.repositories.CategoryRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@RunWith(MockitoJUnitRunner.class)
@SpringBootTest
class CategoryMyServiceTest {

    @MockBean
    private CategoryRepository categoryRepository;

    @Autowired
    private CategoryMyService categoryMyService;

    private Category category;

    @BeforeEach
    void setUp() {
        category = Category.builder()
                .id(9L)
                .nameCategory("Category test")
                .build();
    }

    @Test
    void save() throws SQLException {
        categoryRepository.save(category);
        verify(categoryRepository, times(1)).save(category);
    }

    @Test
    void update() throws SQLException {
        category.setNameCategory("Test category");
        categoryRepository.update(category);
        verify(categoryRepository, times(1)).update(category);
    }

    @Test
    void getById() throws SQLException {
        given(this.categoryRepository.getById(any()))
                .willReturn(category);
        Category categoryTest = categoryMyService.getById(9L);
        assertThat(categoryTest.getId()).isEqualTo(9);
    }

    @Test
    void remove() throws SQLException {
        categoryRepository.delete(9L);
        verify(categoryRepository, times(1)).delete(9L);
    }

    @Test
    void findAll() throws SQLException {
        ArrayList<Category> categoryList = new ArrayList<>();
        categoryList.add(category);
        given(this.categoryRepository.findAll()).willReturn(categoryList);
        List<Category> categoryList1 = categoryMyService.findAll();
        assertThat(categoryList.size() == categoryList1.size());
    }
}