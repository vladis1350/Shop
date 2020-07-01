package com.vladis1350.dao;

import java.sql.*;

public class DatabaseConnection extends DBConfig {

    public Connection getDbConnection() throws SQLException {
            String connectionString = "jdbc:mysql://" + getDB_HOST() + ":" + getDB_PORT() + "/" + getDB_NAME() + "?useUnicode=true&serverTimezone=UTC&useSSL=true&verifyServerCertificate=false";

        return DriverManager.getConnection(connectionString, getDB_USER(), getDB_PASS());
        }
}

