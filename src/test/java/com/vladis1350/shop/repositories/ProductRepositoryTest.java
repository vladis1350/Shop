package com.vladis1350.shop.repositories;

import com.vladis1350.shop.bean.Product;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;

import java.math.BigDecimal;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;

@RunWith(MockitoJUnitRunner.class)
class ProductRepositoryTest {

    @Mock
    private ProductRepository productRepository;

    private Product product;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
        product = Product.builder()
                .id(2)
                .name("Salo")
                .category("1")
                .price(BigDecimal.valueOf(12.60))
                .discount(BigDecimal.valueOf(11.20))
                .build();
    }

    @Test
    void findAll() throws SQLException {
        ArrayList<Product> productList = new ArrayList<>();
        productList.add(product);
        given(this.productRepository.findAll()).willReturn(productList);
        List<Product> productList1 = this.productRepository.findAll();
        assertThat(productList.size() == productList1.size());
    }

    @Test
    void findByProductName() throws SQLException {
        given(this.productRepository.findByProductName(any()))
                .willReturn(product);
        Product product1 = this.productRepository.findByProductName("Salo");
        assertThat(product1.getName()).isEqualTo("Salo");
    }

    @Test
    void getById() throws SQLException {
        given(this.productRepository.getById(any()))
                .willReturn(product);
        Product product1 = this.productRepository.getById(2L);
        assertThat(product1.getId()).isEqualTo(2);
    }

    @Test
    void findProductByCategory() throws SQLException {
        ArrayList<Product> productList = new ArrayList<>();
        productList.add(product);
        given(this.productRepository.findProductByCategory("Meat")).willReturn(productList);
        List<Product> productList1 = this.productRepository.findProductByCategory("Meat");
        assertThat(productList.size() == productList1.size());
    }
}