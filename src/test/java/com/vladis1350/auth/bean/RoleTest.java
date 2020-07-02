package com.vladis1350.auth.bean;

import org.junit.Assert;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class RoleTest {

    private Role roleOne;
    private Role roleTwo;

    @BeforeEach
    void setUp() {
        roleOne = Role.builder()
                .id(1L)
                .roleName(UserRoles.ROLE_GUEST)
                .build();
        roleTwo = Role.builder()
                .id(1L)
                .roleName(UserRoles.ROLE_ADMIN)
                .build();
    }

    @Test
    void testEquals() {
        Assert.assertNotEquals(roleOne, roleTwo);
        roleTwo.setRoleName(UserRoles.ROLE_GUEST);
        Assert.assertEquals(roleOne, roleTwo);
    }

    @Test
    void testHashCode() {
        Assert.assertNotEquals(roleOne.hashCode(), roleTwo.hashCode());
        roleTwo.setRoleName(UserRoles.ROLE_GUEST);
        Assert.assertEquals(roleOne.hashCode(), roleTwo.hashCode());
    }

    @Test
    void testToString() {
        Assert.assertNotEquals(roleOne.toString(), roleTwo.toString());
        roleTwo.setRoleName(UserRoles.ROLE_GUEST);
        Assert.assertEquals(roleOne.toString(), roleTwo.toString());
    }
}