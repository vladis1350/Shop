package com.vladis1350.auth.bean;

import org.junit.Assert;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class UserTest {

    private User userOne;
    private User userTwo;

    @BeforeEach
    void setUp() {
        userOne = User.builder()
                .userName("valoda")
                .firstName("Vladimir")
                .lastName("Petrov")
                .email("valoda.govorit.da@gmail.com")
                .active(true)
                .build();

        userTwo = User.builder()
                .userName("volonter")
                .firstName("Vladimir")
                .lastName("Petrov")
                .email("valoda.govorit.da@gmail.com")
                .active(false)
                .build();
    }

    @Test
    void testEquals() {
        Assert.assertNotEquals(userOne, userTwo);
        userTwo.setActive(true);
        userTwo.setUserName("valoda");
        Assert.assertEquals(userOne, userTwo);
    }

    @Test
    void testHashCode() {
        Assert.assertNotEquals(userOne.hashCode(), userTwo.hashCode());
        userTwo.setActive(true);
        userTwo.setUserName("valoda");
        Assert.assertEquals(userOne.hashCode(), userTwo.hashCode());
    }

    @Test
    void testToString() {
        Assert.assertNotEquals(userOne.toString(), userTwo.toString());
        userTwo.setActive(true);
        userTwo.setUserName("valoda");
        Assert.assertEquals(userOne.toString(), userTwo.toString());
    }
}