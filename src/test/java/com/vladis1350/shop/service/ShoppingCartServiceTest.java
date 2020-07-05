package com.vladis1350.shop.service;

import com.vladis1350.shop.bean.ShoppingCart;
import com.vladis1350.shop.repositories.interfaces.ShoppingCartRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;

import static org.junit.Assert.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@RunWith(MockitoJUnitRunner.class)
class ShoppingCartServiceTest {

    @Mock
    private ShoppingCartRepository shoppingCartRepository;

    @InjectMocks
    private ShoppingCartService shoppingCartService;

    private ShoppingCart shoppingCart;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
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
        boolean isShoppingCart = shoppingCartService.findShoppingCartByIdUserBool(9L);
        assertFalse(isShoppingCart);
    }

    @Test
    void findShoppingCartByIdUserEquals() {
        given(this.shoppingCartRepository.findShoppingCartByIdUser(any()))
                .willReturn(shoppingCart);
        ShoppingCart shoppingCartTest = shoppingCartService.findShoppingCartByIdUser(9L);
        assertEquals(shoppingCartTest, shoppingCart);
    }

    @Test
    void findShoppingCartByIdUserShouldReturnTrue() {
        given(this.shoppingCartRepository.findShoppingCartByIdUser(any()))
                .willReturn(null);
        boolean isShoppingCart = shoppingCartService.findShoppingCartByIdUserBool(9L);
        assertTrue(isShoppingCart);
    }

    @Test
    void findShoppingCartByIdUserNotEquals() {
        given(this.shoppingCartRepository.findShoppingCartByIdUser(any()))
                .willReturn(null);
        ShoppingCart shoppingCartTest = shoppingCartService.findShoppingCartByIdUser(119L);
        assertNotEquals(shoppingCartTest, shoppingCart);
    }
}