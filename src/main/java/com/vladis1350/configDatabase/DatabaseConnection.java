package com.vladis1350.configDatabase;

import java.sql.*;

public class DatabaseConnection extends DBConfig {

    public Connection getDbConnection() throws SQLException {
            String connectionString = "jdbc:mysql://" + dbHost + ":" + dbPort + "/" + dbName + "?useUnicode=true&serverTimezone=UTC&useSSL=true&verifyServerCertificate=false";

        return DriverManager.getConnection(connectionString, dbUser, dbPass);
        }
}

