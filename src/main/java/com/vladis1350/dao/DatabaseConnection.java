package com.vladis1350.dao;

import java.sql.*;

public class DatabaseConnection extends DBConfig {

    public Connection getDbConnection() throws SQLException {
            String connectionString = "jdbc:mysql://" + DB_HOST + ":" + DB_PORT + "/" + DB_NAME + "?useUnicode=true&serverTimezone=UTC&useSSL=true&verifyServerCertificate=false";

        return DriverManager.getConnection(connectionString, DB_USER, DB_PASS);
        }
}

