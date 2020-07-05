package com.vladis1350.validate;

import org.junit.Assert;
import org.junit.jupiter.api.Test;

class UserValidatorFirstAndLastNameTest {

    @Test
    void shouldReturnFalseIfFirstNameIsEmpty() {
        Assert.assertFalse(UserValidator.validateFirstName(""));
        Assert.assertFalse(UserValidator.validateFirstName(null));
    }

    @Test
    void shouldReturnFalseIfLengthFirstNameLessThree() {
        Assert.assertFalse(UserValidator.validateFirstName("Jo"));
    }

    @Test
    void shouldReturnFalseIfLengthFirstNameMoreFiftyFive() {
        Assert.assertFalse(UserValidator.validateFirstName("ssdfsdfsaaaadfwerwerrqeqweqwfwgqrwerrwrwrwrwwwfsvbsefdFE"));
    }

    @Test
    void shouldReturnTrueIfLengthFirstNameMoreThreeAndLessFiftyFive() {
        Assert.assertTrue(UserValidator.validateFirstName("Ivan"));
    }

    @Test
    void shouldReturnFalseIfLastNameIsEmpty() {
        Assert.assertFalse(UserValidator.validateLastName(""));
    }

    @Test
    void shouldReturnFalseIfLengthLastNameLessThree() {
        Assert.assertFalse(UserValidator.validateLastName("Co"));
    }

    @Test
    void shouldReturnFalseIfLengthLastNameMoreFiftyFiveOrNull() {
        Assert.assertFalse(UserValidator.validateLastName("ssdfsdfsaaaadfwerwerrqeqweqwfwgqrwerrwrwrwrwwwfsvbsefdFE"));
        Assert.assertFalse(UserValidator.validateLastName(null));
    }

    @Test
    void shouldReturnTrueIfLengthLastNameMoreThreeAndLessFiftyFive() {
        Assert.assertTrue(UserValidator.validateLastName("Ivanov"));
    }
}
