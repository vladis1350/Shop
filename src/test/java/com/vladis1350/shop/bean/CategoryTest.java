package com.vladis1350.shop.bean;

import org.junit.Assert;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CategoryTest {

    private Category categoryOne;
    private Category categoryTwo;
    private Category categoryThree;
    private Category categoryFour;

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

        categoryThree = new Category();
        categoryFour = new Category();
    }

    @Test
    void testEquals() {
        Assert.assertEquals(categoryOne, categoryTwo);
        Assert.assertEquals(categoryThree, categoryFour);

        categoryTwo.setNameCategory("Category test ");
        Assert.assertNotEquals(categoryOne, categoryTwo);

        categoryThree.setNameCategory("Category test ");
        Assert.assertNotEquals(categoryThree, categoryFour);
    }

    @Test
    void testHashCode() {
        Assert.assertEquals(categoryOne.hashCode(), categoryTwo.hashCode());
        Assert.assertEquals(categoryThree.hashCode(), categoryFour.hashCode());

        categoryTwo.setId(2L);
        Assert.assertNotEquals(categoryOne.hashCode(), categoryTwo.hashCode());

        categoryThree.setId(2L);
        Assert.assertNotEquals(categoryThree.hashCode(), categoryFour.hashCode());
    }

    @Test
    void testToString() {
        Assert.assertEquals(categoryOne.toString(), categoryTwo.toString());
        Assert.assertEquals(categoryThree.toString(), categoryFour.toString());

        categoryOne.setId(3L);
        Assert.assertNotEquals(categoryOne.toString(), categoryTwo.toString());

        categoryFour.setId(3L);
        Assert.assertNotEquals(categoryThree.toString(), categoryFour.toString());
    }
}