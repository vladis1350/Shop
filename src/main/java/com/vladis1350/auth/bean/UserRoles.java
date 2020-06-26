package com.vladis1350.auth.bean;

public enum UserRoles {
    ROLE_ADMIN("Has full access"),
    ROLE_GUEST("Registered, but not participate in any courses");

    private String info;

    UserRoles(String info) {
        this.info = info;
    }
}
