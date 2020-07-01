package com.vladis1350.validate;

import org.junit.Assert;
import org.junit.jupiter.api.Test;

public class UserValidatorUserNameTest {

    @Test
    void shouldReturnFalseIfUserNameIsEmpty() {
        Assert.assertFalse(UserValidator.validateUserName(""));
    }

    @Test
    void shouldReturnFalseIfLengthUserNameLessFive() {
        Assert.assertFalse(UserValidator.validateUserName("bosh"));
    }

    @Test
    void shouldReturnFalseIfLengthUserNameMoreTwenty() {
        Assert.assertFalse(UserValidator.validateUserName("sdfsdfsdfsdfsdfsdfeer23244"));
    }

    @Test
    void shouldReturnTrueIfLengthUserNameMoreFiveAndLessTwenty() {
        Assert.assertTrue(UserValidator.validateUserName("vasily321"));
    }
}
