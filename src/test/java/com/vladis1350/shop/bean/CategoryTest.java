package com.vladis1350.shop.bean;

import org.junit.Assert;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CategoryTest {

    private Category categoryOne;
    private Category categoryTwo;

    @BeforeEach
    void setUp() {
        categoryOne = Category.builder()
                .id(1L)
                .nameCategory("Test category")
                .build();

        categoryTwo = Category.builder()
                .id(1L)
                .nameCategory("Test category")
                .build();
    }

    @Test
    void testEquals() {
        Assert.assertEquals(categoryOne, categoryTwo);
        categoryTwo.setNameCategory("Category test ");
        Assert.assertNotEquals(categoryOne, categoryTwo);
    }

    @Test
    void testHashCode() {
        Assert.assertEquals(categoryOne.hashCode(), categoryTwo.hashCode());
        categoryTwo.setId(2L);
        Assert.assertNotEquals(categoryOne.hashCode(), categoryTwo.hashCode());

    }

    @Test
    void testToString() {
        Assert.assertEquals(categoryOne.toString(), categoryTwo.toString());
        categoryOne.setId(3L);
        Assert.assertNotEquals(categoryOne.toString(), categoryTwo.toString());
    }
}