package com.vladis1350.shop.repositories;

import com.vladis1350.shop.bean.UserShoppingCart;
import com.vladis1350.shop.service.UserShoppingCartMyService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.math.BigDecimal;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@RunWith(MockitoJUnitRunner.class)
@SpringBootTest
class UserShoppingCartRepositoryTest {

    @Autowired
    private UserShoppingCartRepository userShoppingCartRepository;

    private UserShoppingCart userShoppingCart;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
        userShoppingCart = UserShoppingCart.builder()
                .idCart(1L)
                .idProduct(2L)
                .quantityOfGoods(10)
                .price(BigDecimal.valueOf(13.2))
                .amountOfMoney(BigDecimal.valueOf(132.0))
                .build();
    }

    @Test
    void getById() throws SQLException {
        given(this.userShoppingCartRepository.getById(any()))
                .willReturn(userShoppingCart);
        UserShoppingCart userShoppingCartTest = this.userShoppingCartRepository.getById(1L);
        assertThat(userShoppingCartTest.getIdCart()).isEqualTo(1);
    }

    @Test
    void getByProductId() throws SQLException {
        given(this.userShoppingCartRepository.getByProductId(any(), any()))
                .willReturn(userShoppingCart);
        UserShoppingCart userShoppingCartTest = this.userShoppingCartRepository.getByProductId(2L,  1L);
        assertThat(userShoppingCartTest.getIdProduct()).isEqualTo(2);
    }

    @Test
    void findAll() throws SQLException {
        ArrayList<UserShoppingCart> userShoppingCarts = new ArrayList<>();
        userShoppingCarts.add(userShoppingCart);
        given(this.userShoppingCartRepository.findAll())
                .willReturn(userShoppingCarts);
        List<UserShoppingCart> userShoppingCartsActual = this.userShoppingCartRepository.findAll();
        assertThat(userShoppingCartsActual.size() == userShoppingCarts.size());
    }

    @Test
    void findAllById() throws SQLException {
        ArrayList<UserShoppingCart> userShoppingCarts = new ArrayList<>();
        userShoppingCarts.add(userShoppingCart);
        given(this.userShoppingCartRepository.findAllById(userShoppingCart.getIdCart()))
                .willReturn(userShoppingCarts);
        List<UserShoppingCart> userShoppingCartsActual = this.userShoppingCartRepository.findAllById(1L);
        assertThat(userShoppingCartsActual.size() == userShoppingCarts.size());
    }
}