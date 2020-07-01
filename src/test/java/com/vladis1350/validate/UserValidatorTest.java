package com.vladis1350.validate;

import com.vladis1350.auth.bean.User;
import org.junit.Assert;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class UserValidatorTest {

    private User user;

    @BeforeEach
    void setUp() {
        user = User.builder()
                .userName("grishanya")
                .firstName("Grisha")
                .lastName("Ivanov")
                .email("grisha@mail.ru")
                .password("010203").build();
    }

    @Test
    void shouldReturnFalseIfUserNameFalse() {
        user.setUserName("lsd");
        Assert.assertFalse(UserValidator.checkValidateDataUser(user));
    }

    @Test
    void shouldReturnFalseIfFirstNameFalse() {
       user.setFirstName("GR");
        Assert.assertFalse(UserValidator.checkValidateDataUser(user));
    }

    @Test
    void shouldReturnFalseIfLastNameFalse() {
        user.setLastName("DG");
        Assert.assertFalse(UserValidator.checkValidateDataUser(user));
    }

    @Test
    void shouldReturnFalseIfEmailFalse() {
        user.setEmail("grishamail");
        Assert.assertFalse(UserValidator.checkValidateDataUser(user));
    }

    @Test
    void shouldReturnFalseIfPasswordFalse() {
        user.setPassword("010");
        Assert.assertFalse(UserValidator.checkValidateDataUser(user));
    }

    @Test
    void shouldReturnTrueIfDataUserValid() {
        Assert.assertTrue(UserValidator.checkValidateDataUser(user));
    }
}