package com.vladis1350.shop.bean;

import org.junit.Assert;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

class ProductTest {

    private Product productOne;
    private Product productTwo;
    private Product productThree;
    private Product productFour;

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

        productThree = new Product();
        productFour = new Product();
    }

    @Test
    void shouldCompareTwoProductsAndReturnFalse() {
        Assert.assertNotEquals(productOne, productTwo);

        productThree.setName("Smocked meat");
        Assert.assertNotEquals(productThree, productFour);
    }

    @Test
    void shouldCompareTwoProductsAndReturnTrue() {
        productTwo.setName("Smoked fat");
        Assert.assertEquals(productOne, productTwo);

        Assert.assertEquals(productThree, productFour);
    }

    @Test
    void shouldCompareHashCodeTwoProductsAndReturnFalse() {
        Assert.assertNotEquals(productOne.hashCode(), productTwo.hashCode());

        productThree.setName("Smocked meat");
        Assert.assertNotEquals(productThree.hashCode(), productFour.hashCode());
    }

    @Test
    void shouldCompareHashCodeTwoProductsAndReturnTrue() {
        productTwo.setName("Smoked fat");
        Assert.assertEquals(productOne, productTwo);

        Assert.assertEquals(productThree.hashCode(), productFour.hashCode());
    }

    @Test
    void shouldCompareToStringTwoProductsAndReturnFalse() {
        Assert.assertNotEquals(productOne.toString(), productTwo.toString());

        productThree.setName("Smocked meat");
        Assert.assertNotEquals(productThree.toString(), productFour.toString());
    }

    @Test
    void shouldCompareToStringTwoProductsAndReturnTrue() {
        productTwo.setName("Smoked fat");
        Assert.assertEquals(productOne.toString(), productTwo.toString());

        Assert.assertEquals(productThree.toString(), productFour.toString());
    }

}