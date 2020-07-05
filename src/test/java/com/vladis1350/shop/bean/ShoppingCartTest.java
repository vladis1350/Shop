package com.vladis1350.shop.bean;

import org.junit.Assert;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ShoppingCartTest {

    private ShoppingCart shoppingCartOne;
    private ShoppingCart shoppingCartTwo;
    private ShoppingCart shoppingCartThree;
    private ShoppingCart shoppingCartFour;

    @BeforeEach
    void setUp() {
        shoppingCartOne = ShoppingCart.builder()
                .id(1L)
                .idUser(15L)
                .build();

        shoppingCartTwo = ShoppingCart.builder()
                .id(2L)
                .idUser(17L)
                .build();

        shoppingCartThree = new ShoppingCart();
        shoppingCartFour = new ShoppingCart();
    }

    @Test
    void testEquals() {
        Assert.assertNotEquals(shoppingCartOne, shoppingCartTwo);

        shoppingCartOne.setId(2L);
        shoppingCartOne.setIdUser(17L);
        Assert.assertEquals(shoppingCartOne, shoppingCartTwo);

        Assert.assertEquals(shoppingCartThree, shoppingCartFour);

        Assert.assertNotEquals(shoppingCartOne, shoppingCartThree);
        shoppingCartFour.setIdUser(166L);
        Assert.assertNotEquals(shoppingCartThree, shoppingCartFour);
    }

    @Test
    void testHashCode() {
        Assert.assertNotEquals(shoppingCartOne.hashCode(), shoppingCartTwo.hashCode());

        shoppingCartOne.setId(2L);
        shoppingCartOne.setIdUser(17L);
        Assert.assertEquals(shoppingCartOne.hashCode(), shoppingCartTwo.hashCode());

        Assert.assertNotEquals(shoppingCartOne.hashCode(), shoppingCartThree.hashCode());

        Assert.assertEquals(shoppingCartThree.hashCode(), shoppingCartFour.hashCode());

        shoppingCartFour.setId(166L);
        Assert.assertNotEquals(shoppingCartThree.hashCode(), shoppingCartFour.hashCode());
    }

    @Test
    void testToString() {
        Assert.assertNotEquals(shoppingCartOne.toString(), shoppingCartTwo.toString());

        shoppingCartOne.setId(2L);
        shoppingCartOne.setIdUser(17L);
        Assert.assertEquals(shoppingCartOne.toString(), shoppingCartTwo.toString());

        Assert.assertNotEquals(shoppingCartOne.toString(), shoppingCartThree.toString());

        Assert.assertEquals(shoppingCartThree.toString(), shoppingCartFour.toString());
        shoppingCartFour.setId(666L);
        shoppingCartThree.setIdUser(666L);
        Assert.assertNotEquals(shoppingCartThree.toString(), shoppingCartFour.toString());
    }
}