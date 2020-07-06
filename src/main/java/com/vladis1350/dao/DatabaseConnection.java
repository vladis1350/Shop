package com.vladis1350.dao;

import java.sql.*;

import static com.vladis1350.dao.DBConfig.*;

public class DatabaseConnection {

    public Connection getDbConnection() throws SQLException {
            String connectionString = "jdbc:mysql://localhost:3306/product_db?useUnicode=true&serverTimezone=UTC&useSSL=true&verifyServerCertificate=false";

        return DriverManager.getConnection(connectionString, "vladis1350", "010203vv");
        }
}

