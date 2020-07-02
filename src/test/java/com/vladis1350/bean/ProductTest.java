package com.vladis1350.bean;

import com.vladis1350.shop.bean.Product;
import org.junit.Assert;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

class ProductTest {

    private Product productOne;
    private Product productTwo;

    @BeforeEach
    void setUp() {
        productOne = Product.builder()
                .name("Smoked fat")
                .category("Meat")
                .price(BigDecimal.valueOf(11.1))
                .discount(BigDecimal.valueOf(22.2))
                .build();
        productTwo = Product.builder()
                .name("Fat")
                .category("Meat")
                .price(BigDecimal.valueOf(11.1))
                .discount(BigDecimal.valueOf(22.2))
                .build();
    }

    @Test
    void shouldCompareTwoProductsAndReturnFalse() {
        Assert.assertNotEquals(productOne, productTwo);
        productTwo.setName("Smoked fat");
        Assert.assertEquals(productOne, productTwo);
    }

    @Test
    void shouldCompareHashCodeTwoProductsAndReturnFalse() {
        Assert.assertNotEquals(productOne.hashCode(), productTwo.hashCode());
        productTwo.setName("Smoked fat");
        Assert.assertEquals(productOne, productTwo);
    }

}