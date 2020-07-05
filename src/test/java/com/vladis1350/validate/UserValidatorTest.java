package com.vladis1350.validate;

import com.vladis1350.auth.bean.User;
import org.junit.Assert;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class UserValidatorTest {

    private User userOne;
    private User userTwo;

    @BeforeEach
    void setUp() {
        userOne = User.builder()
                .userName("grishanya")
                .firstName("Grisha")
                .lastName("Ivanov")
                .email("grisha@mail.ru")
                .password("010203").build();

        userTwo = User.builder().build();
    }

    @Test
    void shouldReturnFalseIfUserNameFalse() {
        userOne.setUserName("lsd");
        Assert.assertFalse(UserValidator.checkValidateDataUser(userOne));
        Assert.assertFalse(UserValidator.checkValidateDataUser(userTwo));
    }

    @Test
    void shouldReturnFalseIfFirstNameFalse() {
       userOne.setFirstName("GR");
        Assert.assertFalse(UserValidator.checkValidateDataUser(userOne));
    }

    @Test
    void shouldReturnFalseIfLastNameFalse() {
        userOne.setLastName("DG");
        Assert.assertFalse(UserValidator.checkValidateDataUser(userOne));
    }

    @Test
    void shouldReturnFalseIfEmailFalse() {
        userOne.setEmail("grishamail");
        Assert.assertFalse(UserValidator.checkValidateDataUser(userOne));
    }

    @Test
    void shouldReturnFalseIfPasswordFalse() {
        userOne.setPassword("010");
        Assert.assertFalse(UserValidator.checkValidateDataUser(userOne));
    }

    @Test
    void shouldReturnTrueIfDataUserValid() {
        Assert.assertTrue(UserValidator.checkValidateDataUser(userOne));
    }
}