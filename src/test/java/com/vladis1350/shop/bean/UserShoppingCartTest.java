package com.vladis1350.shop.bean;

import org.junit.Assert;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

class UserShoppingCartTest {

    private UserShoppingCart userShoppingCartOne;
    private UserShoppingCart userShoppingCartTwo;
    private UserShoppingCart userShoppingCartThree;
    private UserShoppingCart userShoppingCartFour;

    @BeforeEach
    void setUp() {
        userShoppingCartOne = UserShoppingCart.builder()
                .idCart(100L)
                .idProduct(666L)
                .quantityOfGoods(12)
                .build();

        userShoppingCartTwo = UserShoppingCart.builder()
                .idCart(100L)
                .idProduct(666L)
                .quantityOfGoods(12)
                .build();

        userShoppingCartThree = new UserShoppingCart();

        userShoppingCartFour = new UserShoppingCart();
    }

    @Test
    void testEquals() {
        Assert.assertEquals(userShoppingCartOne, userShoppingCartTwo);

        Assert.assertEquals(userShoppingCartThree, userShoppingCartFour);
    }

    @Test
    void testNotEquals() {
        userShoppingCartTwo.setPrice(BigDecimal.valueOf(150));
        userShoppingCartTwo.setQuantityOfGoods(15);
        Assert.assertNotEquals(userShoppingCartOne, userShoppingCartTwo);

        userShoppingCartThree.setIdProduct(6665L);
        userShoppingCartFour.setIdProduct(66L);
        Assert.assertNotEquals(userShoppingCartThree, userShoppingCartFour);
    }

    @Test
    void testHashCode() {
        Assert.assertNotEquals(userShoppingCartOne.hashCode(), userShoppingCartThree.hashCode());

        Assert.assertEquals(userShoppingCartOne.hashCode(), userShoppingCartTwo.hashCode());

        Assert.assertEquals(userShoppingCartThree.hashCode(), userShoppingCartFour.hashCode());

        userShoppingCartThree.setIdProduct(6665L);
        userShoppingCartFour.setIdProduct(66L);
        Assert.assertNotEquals(userShoppingCartThree.hashCode(), userShoppingCartFour.hashCode());
    }

    @Test
    void testToString() {
        Assert.assertNotEquals(userShoppingCartOne.toString(), userShoppingCartThree.toString());

        Assert.assertEquals(userShoppingCartOne.toString(), userShoppingCartTwo.toString());

        Assert.assertEquals(userShoppingCartThree.toString(), userShoppingCartFour.toString());

        userShoppingCartThree.setIdProduct(6665L);
        userShoppingCartFour.setIdProduct(66L);
        Assert.assertNotEquals(userShoppingCartThree.toString(), userShoppingCartFour.toString());
    }
}