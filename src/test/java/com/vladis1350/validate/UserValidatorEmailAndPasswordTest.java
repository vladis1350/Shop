package com.vladis1350.validate;

import org.junit.Assert;
import org.junit.jupiter.api.Test;

public class UserValidatorEmailAndPasswordTest {

    @Test
    void shouldReturnTrueIfEmailValidate() {
        Assert.assertTrue(UserValidator.validateEmail("zerdonik@gmail.com"));
    }

    @Test
    void shouldReturnFalseIfEmailUnsignedDog() {
        Assert.assertFalse(UserValidator.validateEmail("zerdonikgmail.com"));
    }

    @Test
    void shouldReturnFalseIfEmailNoPoint() {
        Assert.assertFalse(UserValidator.validateEmail("zerdonik@gmailcom"));
    }

    @Test
    void shouldReturnFalseIfEmailShortNDomainName() {
        Assert.assertFalse(UserValidator.validateEmail("zerdonik@gmail."));
        Assert.assertFalse(UserValidator.validateEmail("zerdonik@gmail.c"));
    }

    @Test
    void shouldReturnTrueIfLengthPasswordMoreFive() {
        Assert.assertTrue(UserValidator.validatePassword("123321"));
    }

    @Test
    void shouldReturnFalseIfLengthPasswordLessFive() {
        Assert.assertFalse(UserValidator.validatePassword("123"));
    }
}
