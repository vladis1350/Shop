package com.vladis1350.shop.service;

import com.vladis1350.shop.bean.Product;
import com.vladis1350.shop.repositories.ProductRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.math.BigDecimal;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.AssertionsForInterfaceTypes.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@RunWith(MockitoJUnitRunner.class)
@SpringBootTest
class ProductMyServiceTest {

    private Product product;

    @MockBean
    private ProductRepository productRepository;

    @Autowired
    private ProductMyService productMyService;

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
    void save() throws SQLException {
        productRepository.save(product);
        verify(productRepository, times(1)).save(product);
    }

    @Test
    void update() throws SQLException {
        productRepository.update(product);
        verify(productRepository, times(1)).update(product);
    }

    @Test
    void getById() throws SQLException {
        given(this.productRepository.getById(any()))
                .willReturn(product);
        Product product1 = productMyService.getById(2L);
        assertThat(product1.getId()).isEqualTo(2);
    }

    @Test
    void remove() throws SQLException {
        productRepository.delete(5L);
        verify(productRepository, times(1)).delete(5L);
    }

    @Test
    void findAll() throws SQLException {
        ArrayList<Product> productList = new ArrayList<>();
        productList.add(product);
        given(this.productRepository.findAll()).willReturn(productList);
        List<Product> productList1 = productMyService.findAll();
        assertThat(productList.size() == productList1.size());
    }

    @Test
    void findAllByCategory() {

    }

    @Test
    void findByProductName() throws SQLException {
        given(this.productRepository.findByProductName(any()))
                .willReturn(product);
        Product product1 = productMyService.findByProductName("Salo");
        assertThat(product1.getName()).isEqualTo("Salo");
    }

    @Test
    void changeDiscountForCategories() {

    }
}