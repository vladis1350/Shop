package com.vladis1350.validate;

import com.vladis1350.shop.bean.Product;
import org.junit.Assert;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

class ProductValidatorTest {

    @Test
    void shouldReturnFalseIfProductNameIsEmpty() {
        Assert.assertFalse(ProductValidator.validateName(""));
        Assert.assertFalse(ProductValidator.validateName(null));
    }

    @Test
    void shouldReturnFalseIfLengthProductNameLessThree() {
        Assert.assertFalse(ProductValidator.validateName("Li"));
    }

    @Test
    void shouldReturnTrueIfLengthNameMoreThreeAndLessFiftyFive() {
        Assert.assertTrue(ProductValidator.validateName("Kvass"));
    }

    @Test
    void shouldReturnFalseIfLengthNameMoreFiftyFive() {
        Assert.assertFalse(ProductValidator.validateName(
                "Libdurahmanahibdkhbaskjhdkjfhsjdkhfjksdhfjkshdfsjkdhfsdf"));
    }

    @Test
    void shouldReturnFalseIfPriceLessZero() {
        Assert.assertFalse(ProductValidator.validatePrice(BigDecimal.valueOf(-15)));
    }

    @Test
    void shouldReturnTrueIfPriceMoreZero() {
        Assert.assertTrue(ProductValidator.validatePrice(BigDecimal.valueOf(15.0)));
    }

    @Test
    void shouldReturnFalseIfDiscountLessZero() {
        Assert.assertFalse(ProductValidator.validateDiscount(BigDecimal.valueOf(-15)));
    }

    @Test
    void shouldReturnFalseIfDiscountMoreOneHundred() {
        Assert.assertFalse(ProductValidator.validateDiscount(BigDecimal.valueOf(101)));
    }

    @Test
    void shouldReturnTrueIfDiscountMoreZeroAndLessOneHundred() {
        Assert.assertTrue(ProductValidator.validateDiscount(BigDecimal.valueOf(25)));
    }

    @Test
    void shouldReturnFalseIfNameNotTrue() {
        Product product = Product.builder()
                .name("Li")
                .category("Meat")
                .discount(BigDecimal.valueOf(15))
                .price(BigDecimal.valueOf(14))
                .build();
        Assert.assertFalse(ProductValidator.checkValidateDataProduct(product));
    }

    @Test
    void shouldReturnFalseIfDiscountNotTrue() {
        Product product = Product.builder()
                .name("Kvass")
                .category("Drink")
                .discount(BigDecimal.valueOf(-1))
                .price(BigDecimal.valueOf(14))
                .build();
        Assert.assertFalse(ProductValidator.checkValidateDataProduct(product));
    }

    @Test
    void shouldReturnFalseIfPriceNotTrue() {
        Product product = Product.builder()
                .name("Kvass")
                .category("Drink")
                .discount(BigDecimal.valueOf(10))
                .price(BigDecimal.valueOf(-14))
                .build();
        Assert.assertFalse(ProductValidator.checkValidateDataProduct(product));
    }

    @Test
    void shouldReturnTrueIfDataProductValid() {
        Product product = Product.builder()
                .name("Kvass")
                .category("Drink")
                .discount(BigDecimal.valueOf(10))
                .price(BigDecimal.valueOf(14))
                .build();
        Assert.assertTrue(ProductValidator.checkValidateDataProduct(product));
    }
}