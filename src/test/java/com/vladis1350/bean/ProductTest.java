package com.vladis1350.bean;

import org.junit.Assert;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.*;

class ProductTest {

    @BeforeEach
    void setUp() {
    }

    @Test
    void testEquals() {
        Product productOne = new Product("Coffee", BigDecimal.valueOf(11), "test category", BigDecimal.valueOf(0), null);
        Product productTwo = new Product("Coffee", BigDecimal.valueOf(11), "test category", BigDecimal.valueOf(0), null);
        Product productThree = new Product("Coffee", BigDecimal.valueOf(10), "test category", BigDecimal.valueOf(10), null);

        Assert.assertTrue(productOne.equals(productTwo) && productTwo.equals(productOne));
        Assert.assertNotEquals(productOne, productThree);
    }

    @Test
    void testHashCode() {
        Product productOne = new Product("Coffee", BigDecimal.valueOf(11), "test category", BigDecimal.valueOf(0), null);
        Product productTwo = new Product("Coffee", BigDecimal.valueOf(11), "test category", BigDecimal.valueOf(0), null);
        Product productThree = new Product("Coffee", BigDecimal.valueOf(10), "test category", BigDecimal.valueOf(10), null);

        Assert.assertEquals(productOne.hashCode(), productTwo.hashCode());
        Assert.assertNotEquals(productOne.hashCode(), productThree.hashCode());
    }
}