package com.vladis1350.shop.service;

import com.vladis1350.shop.bean.UserShoppingCart;
import com.vladis1350.shop.repositories.UserShoppingCartRepository;
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

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@RunWith(MockitoJUnitRunner.class)
class UserShoppingCartMyServiceTest {

    @Mock
    private UserShoppingCartRepository userShoppingCartRepository;

    @InjectMocks
    private UserShoppingCartMyService userShoppingCartMyService;

    private UserShoppingCart userShoppingCart;

    @Test
    void save() throws SQLException {
        userShoppingCartRepository.save(userShoppingCart);
        verify(userShoppingCartRepository, times(1)).save(userShoppingCart);
    }

    @Test
    void findAll() throws SQLException {
        ArrayList<UserShoppingCart> userShoppingCarts = new ArrayList<>();
        userShoppingCarts.add(userShoppingCart);
        given(this.userShoppingCartRepository.findAll())
                .willReturn(userShoppingCarts);
        List<UserShoppingCart> userShoppingCartsActual = userShoppingCartMyService.findAll();
        assertThat(userShoppingCartsActual.size() == userShoppingCarts.size());
    }

}