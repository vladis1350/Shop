package com.vladis1350.shop.service;

import com.vladis1350.shop.bean.Product;
import com.vladis1350.shop.repositories.ProductRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;

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
class ProductMyServiceTest {

    private Product product;

    @Mock
    private ProductRepository productRepository;

    @InjectMocks
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
    void getById() throws SQLException {
        given(this.productRepository.getById(any()))
                .willReturn(product);
        Product product1 = productMyService.getById(2L);
        assertThat(product1.getId()).isEqualTo(2);
    }

    @Test
    void findAll() {
        ArrayList<Product> productList = new ArrayList<>();
        productList.add(product);
        given(this.productRepository.findAll()).willReturn(productList);
        List<Product> productList1 = productMyService.findAll();
        assertThat(productList.size() == productList1.size());
    }

    @Test
    void findAllByCategory() throws SQLException {
        ArrayList<Product> productList = new ArrayList<>();
        productList.add(product);
        given(this.productRepository.findProductByCategory("Meat")).willReturn(productList);
        List<Product> productList1 = productMyService.findAllByCategory("Meat");
        assertThat(productList.size() == productList1.size());
    }

    @Test
    void findByProductName() throws SQLException {
        given(this.productRepository.findByName(any()))
                .willReturn(product);
        Product product1 = productMyService.findByProductName("Salo");
        assertThat(product1.getName()).isEqualTo("Salo");
    }

}