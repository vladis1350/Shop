package com.vladis1350.dao;

import lombok.Getter;

@Getter
class DBConfig {

    private DBConfig() {
        throw new IllegalStateException("Utility class");
    }

    protected static final String DB_HOST = "localhost";
    protected static final String DB_PORT = "3306";
    protected static final String DB_USER = "vladis1350";
    protected static final String DB_PASS = "010203vv";
    protected static final String DB_NAME = "product_db";

}
