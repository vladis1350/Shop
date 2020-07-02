package com.vladis1350.shop.service;

import com.vladis1350.shop.bean.ShoppingCart;
import com.vladis1350.shop.repositories.interfaces.ShoppingCartRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@RunWith(MockitoJUnitRunner.class)
@SpringBootTest
class ShoppingCartServiceTest {

    @MockBean
    private ShoppingCartRepository shoppingCartRepository;

    @Autowired
    private ShoppingCartService shoppingCartService;

    private ShoppingCart shoppingCart;

    @BeforeEach
    void setUp() {
        shoppingCart = ShoppingCart.builder()
                .idUser(1L)
                .id(1L)
                .build();
    }

    @Test
    void saveShoppingCart() {
        shoppingCartRepository.save(shoppingCart);
        verify(shoppingCartRepository, times(1)).save(shoppingCart);
    }

    @Test
    void findShoppingCartByIdUserShouldReturnFalse() {
        given(this.shoppingCartRepository.findShoppingCartByIdUser(any()))
                .willReturn(shoppingCart);
        boolean isShoppingCart = shoppingCartService.findShoppingCartByIdUser(9L);
        assertThat(isShoppingCart).isEqualTo(false);
    }

    @Test
    void findShoppingCartByIdUserShouldReturnTrue() {
        given(this.shoppingCartRepository.findShoppingCartByIdUser(any()))
                .willReturn(null);
        boolean isShoppingCart = shoppingCartService.findShoppingCartByIdUser(9L);
        assertThat(isShoppingCart).isEqualTo(true);
    }
}