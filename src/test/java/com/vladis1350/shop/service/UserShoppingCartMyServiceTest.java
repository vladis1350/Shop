package com.vladis1350.shop.service;

import com.vladis1350.shop.bean.Product;
import com.vladis1350.shop.bean.UserShoppingCart;
import com.vladis1350.shop.repositories.UserShoppingCartRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

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
@SpringBootTest
class UserShoppingCartMyServiceTest {

    @MockBean
    private UserShoppingCartRepository userShoppingCartRepository;

    @Autowired
    private UserShoppingCartMyService userShoppingCartMyService;

    private UserShoppingCart userShoppingCart;

    @BeforeEach
    void setUp() {
        userShoppingCart = UserShoppingCart.builder()
                .idCart(1L)
                .idProduct(2L)
                .quantityOfGoods(10)
                .price(BigDecimal.valueOf(13.2))
                .amountOfMoney(BigDecimal.valueOf(132.0))
                .build();
    }

    @Test
    void save() throws SQLException {
        userShoppingCartRepository.save(userShoppingCart);
        verify(userShoppingCartRepository, times(1)).save(userShoppingCart);
    }

    @Test
    void update() throws SQLException {
        userShoppingCartRepository.update(userShoppingCart);
        userShoppingCart.setQuantityOfGoods(5);
        userShoppingCartRepository.update(userShoppingCart);
        verify(userShoppingCartRepository, times(2)).update(userShoppingCart);
    }

    @Test
    void getById() throws SQLException {
        given(this.userShoppingCartRepository.getById(any()))
                .willReturn(userShoppingCart);
        UserShoppingCart userShoppingCartTest = userShoppingCartMyService.getById(1L);
        assertThat(userShoppingCartTest.getIdCart()).isEqualTo(1);
    }

    @Test
    void getByProductId() throws SQLException {
        given(this.userShoppingCartRepository.getByProductId(any(), any()))
                .willReturn(userShoppingCart);
        UserShoppingCart userShoppingCartTest = userShoppingCartMyService.getByProductId(2L,  1L);
        assertThat(userShoppingCartTest.getIdProduct()).isEqualTo(2);
    }

    @Test
    void remove() throws SQLException {
        userShoppingCartRepository.delete(userShoppingCart.getIdCart());
        verify(userShoppingCartRepository, times(1)).delete(1L);
    }

    @Test
    void testRemove() throws SQLException {
        userShoppingCartRepository.deleteProductFromCart(userShoppingCart.getIdCart(), userShoppingCart.getIdProduct());
        verify(userShoppingCartRepository, times(1)).deleteProductFromCart(1L, 2L);
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

    @Test
    void findAllById() throws SQLException {
        ArrayList<UserShoppingCart> userShoppingCarts = new ArrayList<>();
        userShoppingCarts.add(userShoppingCart);
        given(this.userShoppingCartRepository.findAllById(userShoppingCart.getIdCart()))
                .willReturn(userShoppingCarts);
        List<UserShoppingCart> userShoppingCartsActual = userShoppingCartMyService.findAllById(1L);
        assertThat(userShoppingCartsActual.size() == userShoppingCarts.size());
    }
}